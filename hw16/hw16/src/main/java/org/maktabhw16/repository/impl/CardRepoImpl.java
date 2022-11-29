package org.maktabhw16.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.maktabhw16.base.repository.impl.BaseRepositoryImpl;
import org.maktabhw16.entity.bank_entity.Account;
import org.maktabhw16.entity.bank_entity.Card;
import org.maktabhw16.repository.CardRepo;

public class CardRepoImpl extends BaseRepositoryImpl<Card,Long> implements CardRepo {

    public CardRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Card> getEntityClass() {
        return Card.class;
    }

    @Override
    public Card findByCardNumber(String cardNumber) {
        if (isExistByCardNumber(cardNumber)){
        return entityManager.createQuery("from " + getEntityClass().getSimpleName() + " as card where card.cardNumber = :pk", Card.class)
                .setParameter("pk", cardNumber)
                .getSingleResult();
        }else
            throw new NullPointerException("invalid card number");
    }
    @Override
    public boolean isExistByCardNumber(String cardNumber) {
        TypedQuery<Long> query = entityManager.createQuery("select count (id) from " + getEntityClass().getSimpleName() + " as card where card.cardNumber = :pk", Long.class);
        Long count = query.setParameter("pk",cardNumber).getSingleResult();
        return count==1;
    }
}
