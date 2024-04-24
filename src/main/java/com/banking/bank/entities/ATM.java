package com.banking.bank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Make ID, cashLeft
    private Long id;
    private double cashLeft;
    public boolean status;

    // public double getcashLeft() {
    //     return cashLeft;
    // }

    // public void setcashLeft(double cashLeft) {
    //     this.cashLeft = cashLeft;
    // }
}
