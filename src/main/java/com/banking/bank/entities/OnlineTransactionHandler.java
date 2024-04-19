package com.banking.bank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "online_transaction_handler")
public class OnlineTransactionHandler {

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

    public void processTransaction() {
        System.out.println("Transaction processed: " + transactionType + " - Amount: " + amount);
    }

    public void logTransaction() {
        System.out.println("Transaction logged: " + transactionType + " - Amount: " + amount);
    }

    // Constructors, getters, and setters
}