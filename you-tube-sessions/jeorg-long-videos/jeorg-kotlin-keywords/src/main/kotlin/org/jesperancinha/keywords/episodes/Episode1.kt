package org.jesperancinha.keywords.episodes

import org.slf4j.LoggerFactory

val logger: org.slf4j.Logger = LoggerFactory.getLogger(Episode1::class.java)

open class Account(
    val moneyCollection: List<Money>
)

class DebitAccount(moneyCollection: List<Money> = emptyList()) : Account(moneyCollection)

class CreditAccount(moneyCollection: List<Money> = emptyList()) : Account(moneyCollection)

open class Money

open class Note : Money()

open class Coin : Money()

class Card<out A : Account, in M : Money>(
    private val account: A,
) {
    init {
        if(account.moneyCollection.isEmpty()) throw RuntimeException("Account may not be empty!")
    }

    fun addMoney(money: Money) = account

    fun addMoneyForecast(money: Money) = account
    fun addMoneyForecast(money: Money, yearlyRate: (A) -> M): Money {
        addMoneyForecast(money)
        return yearlyRate(account)
    }

    fun addMoneyForecastThis(money: Money, yearlyRate: A.() -> M): Money {
        addMoneyForecast(money)
        return yearlyRate(account)
    }

    override fun toString() = "Welcome to your account"
}


/**
 * inline
 * in
 * out
 * this
 * receiver
 * init
 */
class Episode1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runInlineExample()
            runContravariantExample()
            runCovariantExample()
            runThisReceiverExample()
        }

        private fun runThisReceiverExample() {
            val account = DebitAccount(listOf(Note()))
            val debitCard = Card<DebitAccount, Note>(account)
            debitCard.addMoneyForecast(Note()) { debitAccount ->
                logger.info(debitCard.toString())
                Note()
            }
            debitCard.addMoneyForecastThis(Note()) {
                logger.info(this.toString())
                Note()
            }
        }

        private fun runCovariantExample() {
            val account = DebitAccount(mutableListOf(Note()))
            val debitCard = Card<DebitAccount, Money>(account)
            val creditAccount = CreditAccount(listOf(Note()))
            val creditCard = Card<CreditAccount, Money>(creditAccount)
        }

        private fun runContravariantExample() {
        }


        private fun runInlineExample() {
            processMortgage()
            processMortgage()
        }
    }
}

inline fun processMortgage() = logger.info("Mortgage is being processed...")