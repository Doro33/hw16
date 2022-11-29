package org.maktabhw16.repository;


import org.maktabhw16.base.repository.BaseRepository;
import org.maktabhw16.entity.bank_entity.Account;

import java.util.List;

public interface AccountRepo extends BaseRepository<Account, Long> {
    List<Account> showCustomerAccounts(Long customerId);
}
