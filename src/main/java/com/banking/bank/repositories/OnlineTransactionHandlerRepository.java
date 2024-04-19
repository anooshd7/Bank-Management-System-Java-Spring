package com.banking.bank.repositories;

import com.banking.bank.entities.OnlineTransactionHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineTransactionHandlerRepository extends JpaRepository<OnlineTransactionHandler, Long> {

    List<OnlineTransactionHandler> findByAccountNumber(String accountNumber);
}