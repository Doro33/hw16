package org.maktabhw16.entity.bank_entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.persons.Customer;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account extends BaseEntity<Long> {
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    @Column(unique = true, nullable = false, updatable = false)
    private String accountNumber;

    private LocalDate createAt;

    @Column(nullable = false)
    private double balance;
    @Column(nullable = false)
    private boolean isActive;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id",unique = true)
    private Card card;

    public Account(Customer customer, AccountType accountType, Branch branch, String accountNumber) {
        this.customer = customer;
        this.accountType = accountType;
        this.branch = branch;
        setAccountNumber(accountNumber);
        this.balance=0;
        this.isActive=true;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber.matches("^\\d{10}$"))
            this.accountNumber = accountNumber;
        else
            throw new RuntimeException("This account number is not valid.");
    }
}
