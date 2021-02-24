package br.com.payshare.service.impl;

import br.com.payshare.model.TransactionWallet;
import br.com.payshare.repository.TransactionWalletRepository;
import br.com.payshare.service.TransactionWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionWalletServiceImpl implements TransactionWalletService {

    TransactionWalletRepository transactionWalletRepository;

    @Autowired
    public TransactionWalletServiceImpl(TransactionWalletRepository transactionWalletRepository) {
        this.transactionWalletRepository = transactionWalletRepository;
    }

    @Override
    public TransactionWallet save(TransactionWallet transactionWallet) {
        return transactionWalletRepository.save(transactionWallet);
    }
}
