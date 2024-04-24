package com.banking.bank.controllers;

import com.banking.bank.entities.Account;
import com.banking.bank.entities.ATM;
import com.banking.bank.services.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    private ATMService atmService;

    @GetMapping("/status")
    public boolean getATMStatus() {
        return atmService.getATMStatus();
    }

    @PostMapping("/{username}/deposit")
    public Account depositMoney(@PathVariable String username, @RequestBody Map<String, Double> request, @RequestParam Long atm) {
        Double amount = request.get("amount");
        return atmService.deposit(username, amount, atm);
    }

    @PostMapping("/{username}/withdraw")
    public Account withdrawMoney(@PathVariable String username, @RequestBody Map<String, Double> request, @RequestParam Long atm) {
        Double amount = request.get("amount");
        return atmService.withdraw(username, amount, atm);
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
