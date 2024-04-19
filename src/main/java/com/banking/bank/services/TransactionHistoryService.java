package com.banking.bank.services;

import com.banking.bank.entities.TransactionHistory;
import com.banking.bank.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public List<TransactionHistory> getAllTransactionHistory() {
        return transactionHistoryRepository.findAll();
    }

    public List<TransactionHistory> getTransactionHistoryByAccount(String accountNumber) {
        return transactionHistoryRepository.findByAccountNumber(accountNumber);
    }

    public List<TransactionHistory> getTransactionHistoryByType(String transactionType) {
        return transactionHistoryRepository.findByTransactionType(transactionType);
    }

    public List<TransactionHistory> getTransactionHistoryByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return transactionHistoryRepository.findByTransactionDateBetween(start, end);
    }

    public TransactionHistory addTransactionHistory(TransactionHistory transactionHistory) {
        transactionHistory.setTransactionDate(LocalDateTime.now());
        return transactionHistoryRepository.save(transactionHistory);
    }
}