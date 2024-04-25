package com.banking.bank.repositories;

import com.banking.bank.entities.ATM;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ATMRepository extends JpaRepository<ATM, Long> {
    // findByID
    @Query("SELECT a FROM ATM a WHERE a.id = ?1")
    Optional<ATM> findByID(Long id);

}
