package org.maktabhw16.entity.persons;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.maktabhw16.base.entity.BaseEntity;
import org.maktabhw16.entity.bank_entity.Account;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity<Long> {
   @OneToOne(cascade = CascadeType.ALL)
   private Person person;

   @OneToMany(mappedBy = "customer")
   @ToString.Exclude
   private Set<Account> accountSet = new HashSet<>();

   /*@ManyToMany(mappedBy = "customerSet")
   @ToString.Exclude
   private Set<BankBranch> bankBranchSet = new HashSet<>();*/

   public Customer(Person person) {
      this.person = person;
   }

   public void addBankAccount(Account account){
      accountSet.add(account);
      account.setCustomer(this);
   }
}