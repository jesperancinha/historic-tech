package org.jesperancinha.keywords.episodes;

public class CardJava<A, M> {

    private A account;

    public CardJava(A account) {
        this.account = account;
    }
//    val account: A,
//            ) {
//        init {
//            if (account.moneyCollection.isEmpty()) throw RuntimeException("Account may not be empty!")
//        }
//
//        fun addMoney(money: Money) = account
//
//        fun addMoneyForecast(money: Money) = account
//        fun addMoneyForecast(money: Money, yearlyRate: (A) -> M): Money {
//            addMoneyForecast(money)
//            return yearlyRate(account)
//        }
//
//        fun addMoneyForecastThis(money: Money, yearlyRate: A.() -> M): Money {
//            addMoneyForecast(money)
//            return yearlyRate(account)
//        }
//
//        override fun toString() = "Welcome to your account"
//    }

}
