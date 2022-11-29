package org.maktabhw16.repository;

import org.maktabhw16.base.repository.BaseRepository;
import org.maktabhw16.entity.bank_entity.Card;

public interface CardRepo extends BaseRepository<Card,Long> {
    Card findByCardNumber(String cardNumber);

    boolean isExistByCardNumber(String cardNumber);
}
