package com.banking.bank.repositories;

import com.banking.bank.entities.ATM;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ATMRepository extends JpaRepository<ATM, Long> {
    // Use custom query to find ATM by ID
    @Query("SELECT a FROM ATM a WHERE a.id = ?1")
    ATM findATMById(String id);

}
