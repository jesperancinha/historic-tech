package org.jesperancinha.keywords.episodes

import org.slf4j.LoggerFactory

val logger: org.slf4j.Logger = LoggerFactory.getLogger(Episode1::class.java)

open class Account

class DebitAccount : Account()

class CreditAccount : Account()

open class Money

open class Note : Money()

open class Coin : Money()

class Card<out A : Account, in M : Money>(
    private val account: A
) {
    fun addMoney( money: Money) = account
}


/**
 * inline
 * in
 * out
 * this
 * init
 * receiver
 */
class Episode1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runInlineExample()
            runContravariantExample()
            runCovariantExample()
        }

        private fun runCovariantExample() {
            val account = DebitAccount()
            val debitCard = Card<DebitAccount, Money>(account)
            val creditAccount = CreditAccount()
            val creditCard = Card<CreditAccount, Money> (creditAccount)
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