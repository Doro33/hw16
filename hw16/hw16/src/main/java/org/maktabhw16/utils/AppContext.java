package org.maktabhw16.utils;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.maktabhw16.repository.AccountRepo;
import org.maktabhw16.repository.CardRepo;
import org.maktabhw16.repository.impl.AccountRepoImpl;
import org.maktabhw16.repository.impl.CardRepoImpl;
import org.maktabhw16.service.AccountService;
import org.maktabhw16.service.CardService;
import org.maktabhw16.service.impl.AccountServiceImpl;
import org.maktabhw16.service.impl.CardServiceImpl;


import java.util.Random;
import java.util.Scanner;

public class AppContext {
    @Getter
    private final static Scanner SCANNER;
    @Getter
    private final static Random RANDOM;
    private final static  EntityManager ENTITY_MANAGER;
    private final static AccountRepo BANK_ACC_REPO;
    private final static CardRepo CARD_REPO;
    @Getter
    private final static AccountService BANK_ACC_SERVICE;
    @Getter
    private final static CardService CARD_SERVICE;
    static {
        SCANNER = new Scanner(System.in);
        RANDOM = new Random();
        ENTITY_MANAGER = HibernateUtils.getEntityManagerFactory().createEntityManager();
        BANK_ACC_REPO = new AccountRepoImpl(ENTITY_MANAGER);
        BANK_ACC_SERVICE = new AccountServiceImpl(BANK_ACC_REPO);
        CARD_REPO = new CardRepoImpl(ENTITY_MANAGER);
        CARD_SERVICE = new CardServiceImpl(CARD_REPO);
    }

    public static boolean confirm() {
        System.out.println("Do you confirm?");
        String input;
        while (true) {
            System.out.print("(y:yes n:no): ");
            input = SCANNER.next();
            switch (input.toLowerCase()) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("invalid input.");
            }
        }
    }
}