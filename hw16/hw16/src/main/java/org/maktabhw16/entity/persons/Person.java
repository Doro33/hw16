package org.maktabhw16.entity.persons;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.Address;

import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person extends BaseEntity<Long> {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String nationalCode;

    private LocalDate birthDay;
    private LocalDate dateOfDeath;
    boolean isAlive;
    private String phoneNumber;
    private String homePhone;
    private String job;

    @Enumerated(EnumType.STRING)
    private EducationDegree educationDegree;

    @ManyToOne
    @JoinColumn(name = "home_address_id")
    private Address homeAddress;

    @ManyToOne
    @JoinColumn(name = "work_place_address_id")
    private Address workPlaceAddress;

    public void setNationalCode(String nationalCode) {
        if (nationalCode.matches("^\\d{10}$"))
            this.nationalCode = nationalCode;
        else
            throw new RuntimeException("Invalid national code.");
    }

    public Person(String firstName, String lastName, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        setNationalCode(nationalCode);
    }

    public String getName(){
        return getFirstName()+" "+getLastName();
    }
}