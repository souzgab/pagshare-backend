package br.com.payshare.controller;

import br.com.payshare.api.TransactionApiController;
import br.com.payshare.model.Lobby;
import br.com.payshare.model.Transaction;
import br.com.payshare.model.UserPf;
import br.com.payshare.resttemplate.TransactionRest;
import br.com.payshare.service.LobbyService;
import br.com.payshare.service.TransactionService;
import br.com.payshare.service.UserPfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionController implements TransactionApiController {

    UserPfService userPfService;
    LobbyService lobbyService;
    TransactionService transactionService;

    @Autowired
    public TransactionController(UserPfService userPfService, LobbyService lobbyService, TransactionService transactionService) {
        this.userPfService = userPfService;
        this.lobbyService = lobbyService;
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<List<Transaction>> findAll() throws InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public ResponseEntity<Transaction> findById(long idTransaction) throws InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public ResponseEntity<?> createTransactiobByLobby(String token, BigDecimal amount, long idUser) throws InstantiationException, IllegalAccessException {
        TransactionRest transactionRest = new TransactionRest();
        return new ResponseEntity<>(transactionRest.createTransaction(token, idUser, amount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createTransactionByLobbyWallet(long idUser, BigDecimal amount) throws InstantiationException, IllegalAccessException {
        UserPf userPf = userPfService.findByUserId(idUser);
        Lobby lobby = lobbyService.findByUserPfList(userPf);
        LocalDateTime now = LocalDateTime.now();
        if (userPf.getUserAmount().compareTo(BigDecimal.ZERO) == 0) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.UNAUTHORIZED);
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
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            transactionService.save(transaction);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createTransactionByUserWallet(String token, long idUser, BigDecimal amount) throws InstantiationException, IllegalAccessException {
        TransactionRest transactionRest = new TransactionRest();
        return new ResponseEntity<>(transactionRest.createTransactionWallet(token, idUser, amount), HttpStatus.OK);
    }
}
