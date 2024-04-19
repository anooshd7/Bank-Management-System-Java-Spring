package com.banking.bank.repositories;

import com.banking.bank.entities.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findByAccountNumber(String accountNumber);

    List<TransactionHistory> findByTransactionType(String transactionType);

    List<TransactionHistory> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}