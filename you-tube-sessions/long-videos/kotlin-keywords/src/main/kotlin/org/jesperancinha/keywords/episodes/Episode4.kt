package org.jesperancinha.keywords.episodes

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
            DebitAccount(
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

            val doubleNoteAccount =
                createDoubleNoteAccount<Account>(DebitAccount("Doublerama", (0 until 20).map { Note() }))
            val doubleNoteAccount2 = createDoubleNoteAccount(CreditAccount("Doublerama", (0 until 20).map { Note() }))
            val doubleNoteAccount3 = createDoubleNoteAccount(DebitAccount("Doublerama", (0 until 30).map { Note() }))
            runCatching {
                val doubleNoteAccount4 =
                    createDoubleNoteAccount(CreditAccount("Doublerama", (0 until 10).map { Note() }))
            }.onFailure {
                logger.error("This fails because while we are preserving the type and no error occurs during compilation\nthe runtime fails because an incorrect cast ends up being made")
            }

        }
    }
}

inline fun <reified T : Account> createDoubleNoteAccount(account: T): T = when (account.moneyCollection.size) {
    10 -> DebitAccount(account.name, account.moneyCollection) as T
    20 -> CreditAccount(account.name, account.moneyCollection) as T
    else -> account
}