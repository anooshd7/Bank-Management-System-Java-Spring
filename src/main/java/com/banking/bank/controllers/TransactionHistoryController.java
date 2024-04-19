package com.banking.bank.controllers;

import com.banking.bank.entities.TransactionHistory;
import com.banking.bank.services.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-history")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    // Get all transaction history
    @GetMapping
    public List<TransactionHistory> getAllTransactionHistory() {
        return transactionHistoryService.getAllTransactionHistory();
    }

    // Get transaction history by account number
    @GetMapping("/account/{accountNumber}")
    public List<TransactionHistory> getTransactionHistoryByAccount(@PathVariable String accountNumber) {
        return transactionHistoryService.getTransactionHistoryByAccount(accountNumber);
    }

    // Get transaction history by transaction type
    @GetMapping("/type/{transactionType}")
    public List<TransactionHistory> getTransactionHistoryByType(@PathVariable String transactionType) {
        return transactionHistoryService.getTransactionHistoryByType(transactionType);
    }

    // Get transaction history by date range
    @GetMapping("/date-range")
    public List<TransactionHistory> getTransactionHistoryByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        return transactionHistoryService.getTransactionHistoryByDateRange(startDate, endDate);
    }

    // Add a new transaction history
    @PostMapping
    public TransactionHistory addTransactionHistory(@RequestBody TransactionHistory transactionHistory) {
        return transactionHistoryService.addTransactionHistory(transactionHistory);
    }
}