package com.banking.bank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void recordTransaction() {
        System.out.println("Transaction recorded: " + transactionType + " - Amount: " + amount);
    }

    public void printTransactionDetails() {
        System.out.println("Transaction Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Transaction Date: " + transactionDate);
    }

    // Constructors, getters, and setters
}