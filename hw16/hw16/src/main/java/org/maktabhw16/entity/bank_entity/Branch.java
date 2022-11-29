package org.maktabhw16.entity.bank_entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.maktabhw16.entity.Address;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.persons.Employee;
import org.maktabhw16.entity.persons.Manager;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Branch extends BaseEntity<Long> {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    /*@ManyToMany
    private Set<Customer> customerSet;*/

    @OneToMany(mappedBy = "branch")
    private Set<Employee> employeeSet;

    @OneToMany(mappedBy = "branch")
    private Set<Account> accounts = new HashSet<>();

    public Branch(Address address) {
        this.address = address;
    }
}
