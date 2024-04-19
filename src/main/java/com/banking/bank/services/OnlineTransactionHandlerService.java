package com.banking.bank.services;

import com.banking.bank.entities.OnlineTransactionHandler;
import com.banking.bank.repositories.OnlineTransactionHandlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OnlineTransactionHandlerService {

    @Autowired
    private OnlineTransactionHandlerRepository onlineTransactionHandlerRepository;

    public List<OnlineTransactionHandler> getAllOnlineTransactions() {
        return onlineTransactionHandlerRepository.findAll();
    }

    public OnlineTransactionHandler processOnlineTransaction(OnlineTransactionHandler onlineTransaction) {
        onlineTransaction.setTransactionDate(LocalDateTime.now());
        onlineTransaction.processTransaction();
        onlineTransaction.logTransaction();
        return onlineTransactionHandlerRepository.save(onlineTransaction);
    }

    public List<OnlineTransactionHandler> getOnlineTransactionsByAccount(String accountNumber) {
        return onlineTransactionHandlerRepository.findByAccountNumber(accountNumber);
    }
}