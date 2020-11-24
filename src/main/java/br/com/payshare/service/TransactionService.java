package br.com.payshare.service;

import br.com.payshare.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findByTransactionId(long id);
    List<Transaction> findAll();
    Transaction save(Transaction transaction);
    void deleteById(long id);
}
