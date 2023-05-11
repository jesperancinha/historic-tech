package org.jesperancinha.keywords.episodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CardsAndMortgagesJava {

    private static final Logger logger = LoggerFactory.getLogger(CardsAndMortgagesJava.class);

    public static void main(String[] args) {

        logger.info("Starting Java example!");
        var account = new DebitAccount("Test Debit Holder", List.of(new Note()));
        CardJava<? super DebitAccount, ? extends Money> debitCard = new CardJava<Account, Money>(account);

        var creditAccount = new CreditAccount("Test Credit Holder", List.of(new Note()));
        CardJava<? super DebitAccount, ? extends Money> creditCard = new CardJava<>(creditAccount);
    }
}
