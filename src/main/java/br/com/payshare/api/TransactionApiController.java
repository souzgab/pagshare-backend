package br.com.payshare.api;

import br.com.payshare.PayshareApplication;
import br.com.payshare.model.Transaction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(PayshareApplication.API_PREFIX + "/payshare/transaction")
public interface TransactionApiController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> findAll() throws
    InstantiationException, IllegalAccessException;

    @GetMapping(path = "/{idTransaction}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> findById(@PathVariable long idTransaction)throws
            InstantiationException, IllegalAccessException;

    @PostMapping(path = "/{idUser}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactiobByLobby(@RequestHeader(name = "Authorization") String token
            , @PathVariable BigDecimal amount, @PathVariable long idUser) throws
            InstantiationException, IllegalAccessException;

    @PostMapping(path = "/amount/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransactionByUserWallet(@RequestHeader(name = "Authorization") String token
    ,@PathVariable BigDecimal amount , @PathVariable long idUser)throws
            InstantiationException, IllegalAccessException;


}
