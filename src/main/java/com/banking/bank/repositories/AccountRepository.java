package com.banking.bank.repositories;

import com.banking.bank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

// Even though the interface appears empty, it inherits methods like findById, save, delete, etc., from JpaRepository