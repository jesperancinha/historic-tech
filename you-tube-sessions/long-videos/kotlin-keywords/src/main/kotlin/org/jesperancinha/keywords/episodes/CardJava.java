package org.jesperancinha.keywords.episodes;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class CardJava<A extends Account, M extends Money> {

    private A account;

    public CardJava(A account) {
        if (account.getMoneyCollection().isEmpty()) throw new RuntimeException("Account may not be empty!")
        this.account = account;
    }

    public A addMoney(M money) {
        return account;
    }

    public A addMoneyForecast(M money) {
        return account;
    }

    public M addMoneyForecast(M money, Function<A, M> yearlyRate) {
        addMoneyForecast(money);
        return yearlyRate.apply(account);
    }

    @Override
    public String toString() {
        return "Welcome to your account";
    }

}
