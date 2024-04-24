package com.banking.bank.services;

import com.banking.bank.entities.Account;
import com.banking.bank.entities.ATM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.bank.repositories.AccountRepository;
import com.banking.bank.repositories.ATMRepository;
import java.util.Optional;

@Service
public class ATMService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ATMRepository atmRepository;

    public double checkBalance(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public Account deposit(String atmId, String username, double amount) {
        ATM atm = atmRepository.findATMById(atmId);
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        atm.setCashLeft(atm.getCashLeft() - amount);
        atmRepository.save(atm); // Save the updated ATM
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }
    
    public Account withdraw(String atmId, String username, double amount) {
        Optional<ATM> atm = atmRepository.findById(atmId);

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        atm.setCashLeft(atm.getCashLeft() + amount);
        return accountRepository.save(account);
    }

    public boolean validateLogin(String username, String password) {
        Optional<Account> account = accountRepository.findByUsernameAndPassword(username, password);
        return account.isPresent();
    }

    public boolean validatePIN(String username, String PIN) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getPIN().equals(PIN);
    }

    public void changePIN(String username, String newPIN) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (newPIN.length() != 4) {
            throw new IllegalArgumentException("PIN must be 4 digits long");
        }

        // Update the PIN
        account.setPIN(newPIN);
        accountRepository.save(account);
    }

    public boolean getATMStatus() {
        return true;
    }

    public double getATMCashLeft(String atmId) {
        Optional<ATM> atm = atmRepository.findById(atmId);
        return atm.getCashLeft();

    }

}
