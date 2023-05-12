package org.jesperancinha.keywords.episodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CardsAndMortgagesJava {

    private static final Logger logger = LoggerFactory.getLogger(CardsAndMortgagesJava.class);

    public static void main(String[] args) {

        logger.info("Starting Java example!");
        var account = new DebitAccount("Test Debit Holder", List.of(new Note()));
        CardJava<? extends Account, ? super Note> debitCard = new CardJava<DebitAccount, Money>(account);
        debitCard.addMoney(new Note());

        var creditAccount = new CreditAccount("Test Credit Holder", List.of(new Note()));
        CardJava<? extends CreditAccount, ? super Money> creditCard = new CardJava<>(creditAccount);
        creditCard.addMoney(new Note());
        creditCard.addMoney(new Coin());
        creditCard.addMoney(new Money());
    }
}
