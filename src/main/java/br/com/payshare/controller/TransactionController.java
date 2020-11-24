package br.com.payshare.controller;

import br.com.payshare.api.TransactionApiController;
import br.com.payshare.model.Transaction;
import br.com.payshare.resttemplate.TransactionRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController implements TransactionApiController {

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
    public ResponseEntity<?> createTransactionByUserWallet(String token, long idUser, BigDecimal amount) throws InstantiationException, IllegalAccessException {
        TransactionRest transactionRest = new TransactionRest();
        return new ResponseEntity<>(transactionRest.createTransactionWallet(token, idUser, amount),HttpStatus.OK);
    }
}
