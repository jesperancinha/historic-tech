package org.jesperancinha.keywords.episodes

import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.util.*

val logger: org.slf4j.Logger = LoggerFactory.getLogger(CardsAndMortgages::class.java)

abstract class Account(
    val name: String,
    val moneyCollection: List<Money>,
    private val accountNumber: UUID = UUID.randomUUID(),
) {

    init {
        logger.info("Created with number $accountNumber")
    }

    abstract fun add(note: Note): Account

    override fun toString(): String = "This the account interface!"
}

class DebitAccount(
    name: String,
    moneyCollection: List<Money> = emptyList(),
) : Account(name, moneyCollection) {
    init {
        logger.info("Debit account created!")
    }

    override fun add(note: Note): DebitAccount = DebitAccount(
        name = this.name,
        moneyCollection = this.moneyCollection + note
    )

    override fun toString(): String = "This the debit account interface!"

    fun specialString(): String = "This the special debit account interface function!"
}

class CreditAccount(
    name: String,
    moneyCollection: List<Money> = emptyList(),
) : Account(name, moneyCollection) {
    init {
        logger.info("Credit account created!")
    }

    override fun add(note: Note): CreditAccount = CreditAccount(
        name = this.name,
        moneyCollection = this.moneyCollection + note
    )

    override fun toString(): String = "This the credit account interface!"
}

open class Money {
    val value: BigDecimal = 10.toBigDecimal()
}

open class Note : Money()

open class Coin : Money()

open class Card<out A : Account, in M : Money>(
    val account: A,
) {
    constructor(account: A, message: String) : this(account) {
        logger.info(message)
    }

    init {
        logger.info("Initializing card with account \"{}\"", account)
        if (account.moneyCollection.isEmpty()) throw RuntimeException("Account may not be empty!")
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
 * open
 */
class CardsAndMortgages {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runInlineExample()
            runVarianceExample()
            runThisReceiverExample()
            runInitExample()
        }

        private fun runInitExample() {
            runCatching {
                Card<DebitAccount, Note>(DebitAccount("Test Debit Holder", emptyList()))
            }.onFailure {
                logger.error("It fails because the account is empty and therefore we cannot make a card with it", it)
            }
            Card<DebitAccount, Note>(DebitAccount("Test Debit Holder", listOf(Note())), "A great card!")
        }

        private fun runThisReceiverExample() {
            val account = DebitAccount("Test Debit Holder", listOf(Note()))
            val debitCard = Card<DebitAccount, Note>(account)
            debitCard.addMoneyForecast(Note()) { debitAccount ->
                logger.info("$debitAccount")
                logger.info(debitCard.toString())
                Note()
            }
            debitCard.addMoneyForecastThis(Note()) {
                logger.info(this.toString())
                Note()
            }
        }

        @SuppressWarnings("unchecked")
        private fun runVarianceExample() {
            val account = DebitAccount("Test Debit Holder", listOf(Note()))
            val card: Card<Account, Note> = Card<DebitAccount, Money>(account)
            card.addMoney(Note())
            logger.info("{}", card.account)
            val debitCard = card as Card<DebitAccount, Note>
            debitCard.addMoney(Note())
            logger.info("{}", debitCard.account)
            logger.info("{}", debitCard.account.specialString())

            val creditAccount = CreditAccount("Test Credit Holder", listOf(Note()))
            val creditCard: Card<CreditAccount, Money> = Card(creditAccount)
            creditCard.addMoney(Note())
            creditCard.addMoney(Coin())
            creditCard.addMoney(Money())
        }


        private fun runInlineExample() {
            processMortgage()
            processMortgage()
        }
    }
}

inline fun processMortgage() = logger.info("Mortgage is being processed...")