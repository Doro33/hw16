package org.maktabhw16.service;


import org.maktabhw16.base.service.BaseService;
import org.maktabhw16.entity.bank_entity.Account;

import java.util.List;

public interface AccountService extends BaseService<Account,Long> {
    Account deactivateBankAcc(Account account);
    Account reactivateBankAcc(Account account);
    Account deposit(Account bankAcc, double depositAmount);
    Account withdraw(Account bankAcc, double withdrawAmount);

    public List<Account> showCustomerBankAccount(Long customerId);
}