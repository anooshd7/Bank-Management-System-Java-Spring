package com.banking.bank.controllers;

import com.banking.bank.entities.Account;
import com.banking.bank.entities.ATM;
import com.banking.bank.services.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    private ATMService atmService;

    @PostMapping("/withdraw")
    public Account withdrawMoney(@RequestBody String username, @RequestParam int amount, @RequestParam String atm) {
        return atmService.withdraw(atm, username, amount);
    }

    @GetMapping("/status")
    public boolean getATMStatus() {
        return atmService.getATMStatus();
    }

    @PostMapping("/deposit")
    public Account depositMoney(@RequestBody String username, @RequestParam int amount, @RequestParam String atm) {
        return atmService.deposit(atm, username, amount);

    }

    @PostMapping("/checkBalance")
    public double checkBalance(@RequestBody String username) {
        return atmService.checkBalance(username);
    }

    @PostMapping("/changePIN")
    public void changePIN(@RequestBody String username, @RequestParam String newPIN) {
        atmService.changePIN(username, newPIN);
    }

    @PostMapping("/validatePIN")
    public boolean validatePIN(@RequestBody String username, @RequestParam String PIN) {
        return atmService.validatePIN(username, PIN);
    }
}
