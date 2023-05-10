package org.jesperancinha.keywords.episodes

import javax.accessibility.AccessibleContext

/**
 * operator
 * reified
 * sealed
 * suspend
 * tailrec
 * vararg
 */
class Episode4 {

    companion object {

        operator fun Card<Account, Money>.plus(card: Card<Account, Money>) = Card<Account, Money>(
            Account(
                card.account.name,
                this.account.moneyCollection + card.account.moneyCollection
            )
        )

        @JvmStatic
        fun main(args: Array<String>) {

            val cardA = Card<Account, Money>(DebitAccount("Johanna Dojo Masters", (0 until 10).map { Note() }))
            val cardB = Card<Account, Money>(DebitAccount("John Dojo Masters", (0 until 10).map { Note() }))

            val cardC = cardA + cardB

            logger.info("After processing the resulting card belongs to ${cardC.account.name} and it contains ${cardC.account.moneyCollection.size} elements")

        }
    }
}