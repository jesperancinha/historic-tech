package org.jesperancinha.keywords.episodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CardsAndMortgagesJava {

    private static final Logger logger = LoggerFactory.getLogger(CardsAndMortgagesJava.class);

    public static void main(String[] args) {
        logger.info("Starting Java example!");
        testInvarianceExample();
        testVarianceExample();
    }

    private static void testInvarianceExample() {
        var account = new DebitAccount("Test Debit Holder", List.of(new Note()));
        // Invariant because it doe not vary. Literally
        CardJava<Account, Note> card = new CardJava<>(account);
        // This will not work
        // CardJava<Account, Note> card = new CardJava<DebitAccount, Money>(account);
        // card.addMoney(new Money());
        // card.addMoney(new Coin());
        card.addMoney(new Note());
        logger.info("{}", card.getAccount());
    }

    @SuppressWarnings("unchecked")
    private static void testVarianceExample() {
        var account = new DebitAccount("Test Debit Holder", List.of(new Note()));
        CardJava<? extends Account, ? super Note> card = new CardJava<DebitAccount, Money>(account);
        card.addMoney(new Note());
        logger.info("{}", card.getAccount());
        // Not possible
        // logger.info("{}", card.getAccount().specialString());

        CardJava<? extends DebitAccount, ? super Note> debitCard = (CardJava<? extends DebitAccount, ? super Note>) card;
        debitCard.addMoney(new Note());
        logger.info("{}", debitCard.getAccount());
        logger.info("{}", debitCard.getAccount().specialString());

        var creditAccount = new CreditAccount("Test Credit Holder", List.of(new Note()));
        CardJava<? extends CreditAccount, ? super Money> creditCard = new CardJava<>(creditAccount);
        creditCard.addMoney(new Note());
        creditCard.addMoney(new Coin());
        creditCard.addMoney(new Money());
    }
}
