package org.maktabhw16.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.maktabhw16.base.entity.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address extends BaseEntity<Long> {
    private String name;
    private String postalCode;
    private String houseNumber;
    private String alley;
    private String street;
    private String city;
    private String State;
    private String country;
    public Address(String name, String postalCode, String houseNumber, String alley,
                   String street, String city, String state, String country) {
        this.name = name;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.alley = alley;
        this.street = street;
        this.city = city;
        State = state;
        this.country = country;
    }

    public Address(String city) {
        this.city = city;
    }
}