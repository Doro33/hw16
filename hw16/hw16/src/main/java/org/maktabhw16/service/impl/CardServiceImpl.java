package org.maktabhw16.service.impl;

import jakarta.persistence.EntityTransaction;
import org.maktabhw16.base.service.impl.BaseServiceImpl;
import org.maktabhw16.entity.bank_entity.Account;
import org.maktabhw16.entity.bank_entity.Card;
import org.maktabhw16.repository.CardRepo;
import org.maktabhw16.service.CardService;
import org.maktabhw16.utils.AppContext;

public class CardServiceImpl extends BaseServiceImpl<Card, Long, CardRepo> implements CardService {

    public CardServiceImpl(CardRepo repository) {
        super(repository);
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        if (isExistByCardNumber(cardNumber))
            return repository.findByCardNumber(cardNumber);
        else
            throw new RuntimeException("invalid card number");
    }

    @Override
    public boolean isExistByCardNumber(String cardNumber) {
        return repository.isExistByCardNumber(cardNumber);
    }

    @Override
    public void moneyTransfer(Card payer, Card recipient, double amount) {
        if (payer.isActive()) {
            if (recipient.isActive()) {
                if (payer.getAccount().getBalance() >= (amount + 600)) {
                    EntityTransaction transaction = repository.getEntityManager().getTransaction();
                   /* try {
                        transaction.begin();*/
                        payer.getAccount().setBalance(
                                payer.getAccount().getBalance() - (amount + 600));

                        AppContext.getBANK_ACC_SERVICE().update(payer.getAccount());

                        recipient.getAccount().setBalance(
                                recipient.getAccount().getBalance() + amount);

                        AppContext.getBANK_ACC_SERVICE().update(recipient.getAccount());
                   /*     transaction.commit();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        transaction.rollback();
                    }*/
                }else
                    System.out.println("insufficient inventory");
            }
            else
                System.out.println("recipient card is deactivated");
        }else
            System.out.println("payer card is deactivated");
    }
}
