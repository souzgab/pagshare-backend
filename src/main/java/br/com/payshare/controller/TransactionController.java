package br.com.payshare.controller;

import br.com.payshare.api.TransactionApiController;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.Transaction;
import br.com.payshare.model.TransactionWallet;
import br.com.payshare.model.UserPf;
import br.com.payshare.resttemplate.TransactionRest;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.TransactionService;
import br.com.payshare.service.TransactionWalletService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
public class TransactionController implements TransactionApiController {

    UserPfService userPfService;
    LobbyService lobbyService;
    TransactionService transactionService;
    TransactionWalletService transactionWalletService;

    @Autowired
    public TransactionController(UserPfService userPfService, LobbyService lobbyService, TransactionService transactionService, TransactionWalletService transactionWalletService) {
        this.userPfService = userPfService;
        this.lobbyService = lobbyService;
        this.transactionService = transactionService;
        this.transactionWalletService = transactionWalletService;
    }

    @Override
    public ResponseEntity<?> findAll() throws InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(long idTransaction) throws InstantiationException, IllegalAccessException {
        Transaction transaction = transactionService.findByTransactionId(idTransaction);
        if (transaction == null ){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction,HttpStatus.OK) ;
    }

    //
    @Override
    public ResponseEntity<?> createTransactiobByLobby(String token, BigDecimal amount, long idUser) throws InstantiationException, IllegalAccessException {
        TransactionRest transactionRest = new TransactionRest();
        return new ResponseEntity<>(transactionRest.createTransaction(token, idUser, amount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createTransactionTransfer(long idUserCurrent, BigDecimal amount, String cpfDocument) throws InstantiationException, IllegalAccessException {
        UserPf userPfCurrent = userPfService.findByUserId(idUserCurrent);
        UserPf userPfSend = userPfService.findByCpf(cpfDocument);
        LocalDateTime now = LocalDateTime.now();

        System.out.println("nome do usuariooooo " + userPfSend.getName());

        if (userPfCurrent.getUserAmount().compareTo(BigDecimal.ZERO) == 0)
            return new ResponseEntity<>("Insufficient_funds", HttpStatus.UNAUTHORIZED);

        if (amount.compareTo(BigDecimal.ZERO) == 0)
            return new ResponseEntity<>("Valor tem que ser maior que 0", HttpStatus.BAD_REQUEST);

        if (userPfCurrent == null || userPfSend == null)
            return new ResponseEntity<>("Algum dos usuarios não foram achados", HttpStatus.NOT_FOUND);

        Transaction transaction = new Transaction();
        TransactionWallet transactionWallet = new TransactionWallet();

        if (userPfCurrent != null && userPfSend != null) {
            try {
                transaction.setAmount(amount);
                transaction.setCupomUser(userPfCurrent.getName());
                transaction.setCreatedAt(now);
                transaction.setExpirationDate(now);
                transaction.setCurrencyId("BRL");
                transaction.setDescription("Transferência");
                transaction.setExternalReference("transferência wallet");
                transaction.setPaymentMethod("wallet");
                transaction.setInitPoint("wallet");
                transaction.setStatus("Approved");
                transaction.setLobby(null);
                transaction.setUserPf(userPfCurrent);
                userPfCurrent.setUserAmount(userPfCurrent.getUserAmount().subtract(transaction.getAmount()));
            }catch (Exception e) {
                System.out.println(e);
            } finally {
                transactionService.save(transaction);
            }

            try {
                transactionWallet.setAmount(amount);
                transactionWallet.setCurrencyId("BRL");
                transactionWallet.setCreatedAt(now);
                transactionWallet.setExpirationDate(now);
                transactionWallet.setDescription("Recebimento de transferência de " + userPfCurrent.getName());
                transactionWallet.setStatus("Aprroved");
                transactionWallet.setExternalReference("Entry amount");
                transactionWallet.setInitPoint("wallet");
                transactionWallet.setPaymentMethod("Receivable amount");
                transactionWallet.setUserPf(userPfSend);
                userPfSend.setUserAmount(userPfSend.getUserAmount().add(transactionWallet.getAmount()));
            }catch (Exception e) {
                System.out.println(e);
            }finally {
                transactionWalletService.save(transactionWallet);
            }
        }

        return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);
    }

    // realiza pagamento com a wallet
    @Override
    public ResponseEntity<?> createTransactionByLobbyWallet(long idUser, BigDecimal amount) throws InstantiationException, IllegalAccessException {
        UserPf userPf = userPfService.findByUserId(idUser);
        Lobby lobby = lobbyService.findByUserPfList(userPf);
        LocalDateTime now = LocalDateTime.now();
        if (userPf.getUserAmount().compareTo(BigDecimal.ZERO) == 0 || userPf.getUserAmountLobby().compareTo(userPf.getUserAmount()) == 1) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.UNAUTHORIZED);
        }
        if (userPf.getUserAmountLobby().compareTo(BigDecimal.ZERO) == 0){
            return new ResponseEntity<>("Você já pagou a sua parte", HttpStatus.OK);
        }
        Transaction transaction = new Transaction();
        try {
            transaction.setAmount(amount);
            transaction.setCreatedAt(now);
            transaction.setExpirationDate(now.plusHours(48));
            transaction.setCurrencyId("BRL");
            transaction.setDescription(lobby.getLobbyDescription());
            transaction.setExternalReference("wallet" + now);
            transaction.setPaymentMethod("wallet");
            transaction.setInitPoint("wallet");
            transaction.setStatus("approved");
            transaction.setCupomUser(userPf.getName());
            transaction.setLobby(lobby);
            transaction.setUserPf(userPf);
            userPf.setUserAmount(userPf.getUserAmount().subtract(transaction.getAmount()));
            userPf.setUserAmountLobby(transaction.getAmount().subtract(userPf.getUserAmountLobby()));
            lobby.setAmountTotal(transaction.getAmount().add(lobby.getAmountTotal()));
            lobby.setLobbyOpen(false); // assim que e feito a primeira transação a lobby sera fechada para novos participantes impossibilitando exclusão
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            transactionService.save(transaction);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    // adiciona dinheiro na carteira
    @Override
    public ResponseEntity<?> createTransactionByUserWallet(String token, long idUser, BigDecimal amount) throws InstantiationException, IllegalAccessException {
        TransactionRest transactionRest = new TransactionRest();
        return new ResponseEntity<>(transactionRest.createTransactionWallet(token, idUser, amount), HttpStatus.OK);
    }
}
