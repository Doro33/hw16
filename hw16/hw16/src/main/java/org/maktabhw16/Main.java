package org.maktabhw16;

import org.maktabhw16.entity.Address;
import org.maktabhw16.entity.bank_entity.AccountType;
import org.maktabhw16.entity.bank_entity.Account;
import org.maktabhw16.entity.bank_entity.Branch;
import org.maktabhw16.entity.bank_entity.Card;
import org.maktabhw16.entity.persons.Employee;
import org.maktabhw16.entity.persons.Manager;
import org.maktabhw16.entity.persons.Customer;
import org.maktabhw16.entity.persons.Person;
import org.maktabhw16.utils.AppContext;

public class Main {
    public static void main(String[] args) {

       // HibernateUtils.getEntityManagerFactory();
        Person person = new Person("ali", "moradi", "0123456789");
        Customer customer = new Customer(person);
        Address address = new Address("tehran");
        Branch branch = new Branch(address);
        Employee employee = new Employee(person, 10_000, branch);
        Manager manager = new Manager(employee, branch);
        Account account = new Account(customer, AccountType.CHECKING_ACCOUNT, branch,
                "0123456789");
        Account account1 = new Account(customer, AccountType.INTEREST_FREE, branch,
                "9876543210");
        Card card = new Card(account,"1111222233334444","1234");
        //account.setCard(card);

        AppContext.getBANK_ACC_SERVICE().save(account);
        AppContext.getBANK_ACC_SERVICE().save(account1);
        System.out.println(AppContext.getBANK_ACC_SERVICE().showCustomerBankAccount(1L));

        AppContext.getCARD_SERVICE().save(card);
        System.out.println(AppContext.getCARD_SERVICE().findByCardNumber(card.getCardNumber()));

        System.out.println(card);
        System.out.println(card.getId());
        System.out.println(account.getCard());
    }
}