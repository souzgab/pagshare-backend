package br.com.payshare.repository;

import br.com.payshare.model.TransactionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionWalletRepository extends JpaRepository<TransactionWallet , Long> {

}
