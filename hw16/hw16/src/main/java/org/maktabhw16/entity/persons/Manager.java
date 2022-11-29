package org.maktabhw16.entity.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.bank_entity.Branch;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Manager extends BaseEntity<Long> {
    /*@OneToOne
    private Person person;*/
    @OneToOne
    private Employee employee;

    private LocalDate hireAsManagerAt;

    @OneToOne
    private Branch branch;

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private Set<Employee> employeeSet;

    public Manager(Employee employee, Branch branch) {
        this.employee = employee;
        this.branch = branch;
    }
}
