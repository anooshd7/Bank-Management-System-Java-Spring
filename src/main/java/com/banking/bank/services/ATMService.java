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
        System.out.println("Checking balance for " + username);
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found here!"));
        return account.getBalance();
    }

    public Account deposit(String username, double amount, Long atmId) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Optional<ATM> optionalATM = atmRepository.findById(atmId);
        if (optionalATM.isPresent()) {
            ATM atm = optionalATM.get();
            if (atm.getCashLeft() < amount) {
                throw new RuntimeException("ATM does not have enough cash");
            }
            // Perform deposit operation
            account.setBalance(account.getBalance() + amount);
            atm.setCashLeft(atm.getCashLeft() - amount);
            atmRepository.save(atm);
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("ATM not found");
        }
    }

    public Account withdraw(String username, double amount, Long atmId) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Optional<ATM> optionalATM = atmRepository.findById(atmId);

        if (optionalATM.isPresent()) {
            ATM atm = optionalATM.get();
            if (atm.getCashLeft() < amount) {
                throw new RuntimeException("ATM does not have enough cash");
            }

            // Perform withdraw operation
            double newBalance = account.getBalance() - amount;
            if (newBalance < 0) {
                throw new RuntimeException("Insufficient funds");
            }

            account.setBalance(newBalance);
            atm.setCashLeft(atm.getCashLeft() + amount);
            atmRepository.save(atm);
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("ATM not found");
        }
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

    public Account changePIN(String username, String newPIN) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (newPIN.length() != 4) {
            throw new IllegalArgumentException("PIN must be 4 digits long");
        }
        account.setPIN(newPIN);
        System.out.println("New PIN: " + account.getPIN());
        return accountRepository.save(account);
    }

    public boolean getATMStatus() {
        return true;
    }

    public double getATMCashLeft(String atmId) {
        Optional<ATM> optionalATM = atmRepository.findById(Long.valueOf(atmId));

        if (optionalATM.isPresent()) {
            ATM atm = optionalATM.get();
            return atm.getCashLeft();
        } else {
            throw new RuntimeException("ATM not found");
        }
    }

}
