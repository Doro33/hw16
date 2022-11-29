package org.maktabhw16.entity.bank_entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.utils.AppContext;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Card extends BaseEntity<Long> {

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Account account;

    private String cardNumber;
    private short cvv2;
    private String password;
    private String secondPassword;
    private LocalDate expirationAt;
    private boolean isActive;

    public Card(Account account, String cardNumber, String password) {
        setAccount(account);
        setCardNumber(cardNumber);
        this.cvv2 = (short) AppContext.getRANDOM().nextInt(100,10000);
        setPassword(password);
        this.expirationAt = LocalDate.now().plusYears(4);
        this.isActive=true;
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber.matches("^\\d{16}$") || cardNumber.matches("^\\d{12}$"))
            this.cardNumber = cardNumber;
        else
            throw new RuntimeException("This card number is not valid.");
    }

    public void setPassword(String password) {
        if (password.matches("^\\d{4}$"))
            this.password=password;
        else
            throw new RuntimeException("This password is not valid.");
    }

    public void setAccount(Account account) {
        this.account = account;
        account.setCard(this);
    }
}
