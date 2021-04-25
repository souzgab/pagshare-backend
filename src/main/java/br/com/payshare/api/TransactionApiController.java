package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.model.Transaction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/transaction")
public interface TransactionApiController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() throws
            InstantiationException, IllegalAccessException;

    @GetMapping(path = "/{idTransaction}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable long idTransaction) throws
            InstantiationException, IllegalAccessException;

    //cria transação com mercadoPago
    @PostMapping(path = "/{idUser}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactiobByLobby(@RequestHeader(name = "Authorization") String token
            , @PathVariable BigDecimal amount, @PathVariable long idUser) throws
            InstantiationException, IllegalAccessException;

    // cria pagamento da lobby com a carteira
    @PostMapping(path = "/wallet-lobby/{idUser}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactionByLobbyWallet(@PathVariable long idUser, @PathVariable BigDecimal amount)throws
            InstantiationException, IllegalAccessException;

    // cria transferencia entre contas
    @PostMapping(path = "/wallet-transfer/{idUserCurrent}/{amount}/{cpfDocument}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactionTransfer(@PathVariable long idUserCurrent , @PathVariable BigDecimal amount , @PathVariable String cpfDocument)throws
            InstantiationException, IllegalAccessException;

    // adiciona dinheiro na carteira
    @PostMapping(path = "/wallet/{idUser}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactionByUserWallet(@RequestHeader(name = "Authorization") String token
            , @PathVariable long idUser, @PathVariable BigDecimal amount) throws
            InstantiationException, IllegalAccessException;


}
