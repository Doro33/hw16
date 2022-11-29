package org.maktabhw16.repository.impl;

import jakarta.persistence.EntityManager;
import org.maktabhw16.base.repository.impl.BaseRepositoryImpl;
import org.maktabhw16.entity.bank_entity.Account;
import org.maktabhw16.repository.AccountRepo;

import java.util.List;


public class AccountRepoImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepo {

    public AccountRepoImpl(EntityManager em) {
        super(em);
    }
    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public List<Account> showCustomerAccounts(Long customerId) {
        return entityManager.createQuery
                        ("from " + getEntityClass().getSimpleName() + " as acc where acc.customer.id= :pk", Account.class)
        .setParameter("pk", customerId)
        .getResultList();
    }
}