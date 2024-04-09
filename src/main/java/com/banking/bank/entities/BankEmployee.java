package com.banking.bank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
public class BankEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String empID;
    private String address;
    private int contact;
    private int salary;

    // @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    // private List<Account> accounts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public void verifyTransaction() {
        // Implement transaction verification logic
    }
}
