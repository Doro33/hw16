package org.maktabhw16;

import org.maktabhw16.entity.bank_entity.Card;
import org.maktabhw16.utils.AppContext;


public class moneyTransfer {
    public static void main(String[] args) {
        card2card();
    }

    private static Card getCard() {
        String cardNumber;
        while (true) {
            System.out.print("enter card number: ");
            cardNumber = AppContext.getSCANNER().next();
            if ((cardNumber.matches("^\\d{16}$") || cardNumber.matches("^\\d{12}$"))
                    && (AppContext.getCARD_SERVICE().isExistByCardNumber(cardNumber)))
                return AppContext.getCARD_SERVICE().findByCardNumber(cardNumber);
            else System.out.println("invalid card number");
        }
    }

    private static boolean checkCvv2(Card card) {
        String cvv2;
        while (true) {
            System.out.print("cvv2: ");
            try {
                cvv2 = AppContext.getSCANNER().next();
                if (Short.parseShort(cvv2) == card.getCvv2())
                    return true;
                else
                    System.out.println("invalid cvv2");
            } catch (Exception e) {
                System.out.println("invalid cvv2");
            }
        }
    }

    private static boolean checkExpYear(Card card) {
        String year;
        while (true) {
            System.out.print("exp year: ");
            try {
                year = AppContext.getSCANNER().next();
                if (Integer.parseInt(year) == card.getExpirationAt().getYear() % 100)
                    return true;
                else
                    System.out.println("incorrect");
            } catch (Exception e) {
                System.out.println("incorrect");
            }
        }
    }

    private static boolean checkExpMonth(Card card) {
        String month;
        while (true) {
            System.out.print("exp month:");
            try {
                month = AppContext.getSCANNER().next();
                if (Integer.parseInt(month) == card.getExpirationAt().getMonthValue())
                    return true;
                else
                    System.out.println("incorrect");
            } catch (Exception e) {
                System.out.println("incorrect");
            }
        }
    }

    private static boolean checkPassword(Card card) {
        String password;
        for (int i = 0; i <= 2; i++) {
            System.out.print("password: ");
            password = AppContext.getSCANNER().next();
            if (password.equals(card.getSecondPassword()))
                return true;
            else
                System.out.println("incorrect");
        }
        card.setActive(false);
        AppContext.getCARD_SERVICE().update(card);
        return false;
    }

    private static boolean checkCardInformation(Card card) {
        return checkCvv2(card) && checkExpMonth(card) && checkExpYear(card) && checkPassword(card);
    }

    private static Card setRecipientCard() {
        while (true) {
            Card card = getCard();
            System.out.println(card.getAccount().getCustomer().getPerson().getName());
            if (AppContext.confirm())
                return card;
        }
    }


    private static double setTransactionAmount() {
        String input;
        double amount;
        while (true) {
            System.out.print("amount: ");
            input = AppContext.getSCANNER().next();
            try {
                amount = Double.parseDouble(input);
                if (amount > 0)
                    return amount;
                else
                    System.out.println("invalid amount");
            } catch (Exception exception) {
                System.out.println("invalid amount");
            }
        }
    }

    private static void card2card() {
        System.out.println("Payer card:");
        Card payer = getCard();
        System.out.println("Recipient card:");
        Card recipient = setRecipientCard();
        double amount = setTransactionAmount();
        checkCardInformation(payer);
        AppContext.getCARD_SERVICE().moneyTransfer(payer,recipient,amount);
    }

}