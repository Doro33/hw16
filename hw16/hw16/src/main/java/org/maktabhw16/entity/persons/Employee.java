package org.maktabhw16.entity.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.bank_entity.Branch;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee extends BaseEntity<Long> {
    @OneToOne
    private Person person;

    private double salary;
    private LocalDate hireAt;
    private LocalDate leavingAt;

    @ManyToOne
    @JoinColumn(name = "branch_id",nullable = false)
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public Employee(Person person, double salary, Branch branch) {
        this.person = person;
        this.salary = salary;
        this.branch = branch;
        //todo: set  manager
    }
}