package org.maktabhw16.service;

import org.maktabhw16.base.service.BaseService;
import org.maktabhw16.entity.bank_entity.Card;

public interface CardService extends BaseService<Card,Long> {
    Card findByCardNumber(String cardNumber);

    boolean isExistByCardNumber(String cardNumber);

    void moneyTransfer(Card payer,Card recipient,double amount);
}
