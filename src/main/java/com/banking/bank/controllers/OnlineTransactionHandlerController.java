package com.banking.bank.controllers;

import com.banking.bank.entities.OnlineTransactionHandler;
import com.banking.bank.services.OnlineTransactionHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/online-transactions")
public class OnlineTransactionHandlerController {

    @Autowired
    private OnlineTransactionHandlerService onlineTransactionHandlerService;

    @GetMapping
    public List<OnlineTransactionHandler> getAllOnlineTransactions() {
        return onlineTransactionHandlerService.getAllOnlineTransactions();
    }

    @PostMapping
    public OnlineTransactionHandler processOnlineTransaction(@RequestBody OnlineTransactionHandler onlineTransaction) {
        return onlineTransactionHandlerService.processOnlineTransaction(onlineTransaction);
    }

    @GetMapping("/account/{accountNumber}")
    public List<OnlineTransactionHandler> getOnlineTransactionsByAccount(@PathVariable String accountNumber) {
        return onlineTransactionHandlerService.getOnlineTransactionsByAccount(accountNumber);
    }
}