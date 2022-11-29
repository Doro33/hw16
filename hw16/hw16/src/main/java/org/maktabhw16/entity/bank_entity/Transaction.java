package org.maktabhw16.entity.bank_entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.maktabhw16.base.entity.BaseEntity;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity<Long> {
    @OneToOne
    @JoinColumn(name = "payer_id")
    private Card payer;

    @OneToOne
    @JoinColumn(name = "recipient_id")
    private Card recipient;

    private double amount;
    private LocalDate date;
}
