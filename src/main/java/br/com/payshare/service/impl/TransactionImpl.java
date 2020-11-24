package br.com.payshare.service.impl;

import br.com.payshare.model.Transaction;
import br.com.payshare.repository.TransactionRepository;
import br.com.payshare.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionImpl implements TransactionService {

    TransactionRepository transactionRepository;

    @Autowired
    public TransactionImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findByTransactionId(long id) {
        return transactionRepository.findByTransactionId(id);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(long id) {
        transactionRepository.deleteById(id);
    }
}
