package org.maktabhw16.service.impl;

import org.maktabhw16.base.service.impl.BaseServiceImpl;
import org.maktabhw16.entity.bank_entity.Account;
import org.maktabhw16.repository.AccountRepo;
import org.maktabhw16.service.AccountService;

import java.util.List;

public class AccountServiceImpl extends BaseServiceImpl<Account,Long, AccountRepo>
        implements AccountService {
    public AccountServiceImpl(AccountRepo repository) {
        super(repository);
    }

    @Override
    public Account deactivateBankAcc(Account account) {
        account.setActive(false);
        return update(account);
    }

    @Override
    public Account reactivateBankAcc(Account account) {
        account.setActive(true);
        return update(account);
    }

    @Override
    public Account deposit(Account bankAcc, double depositAmount) {
        if (bankAcc.isActive()) {
            bankAcc.setBalance(bankAcc.getBalance() + depositAmount);
             update(bankAcc);
        }else
            throw new RuntimeException("This Bank account is deactivated.");
        return bankAcc;
    }

    @Override
    public Account withdraw(Account bankAcc, double withdrawAmount) {
        if (bankAcc.isActive()) {
            if (bankAcc.getBalance() >= withdrawAmount) {
                bankAcc.setBalance(bankAcc.getBalance() - withdrawAmount);
                update(bankAcc);
            }else
                throw new RuntimeException("Insufficient funds.");
        }else
            throw new RuntimeException("This Bank account is deactivated.");
        return bankAcc;
    }

    @Override
    public List<Account> showCustomerBankAccount(Long customerId) {
      return repository.showCustomerAccounts(customerId);
    }
}