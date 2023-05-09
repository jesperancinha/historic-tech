package org.jesperancinha.keywords.episodes

/**
 * infix
 * inner
 * internal
 * by
 * lateinit
 */
class Episode3 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val creditAccount = CreditAccount(
                name = "Credit account holder",
                moneyCollection = listOf(Note())
            )
            creditAccount incasso DebitAccount(
                name = "Debit account holder",
                moneyCollection = listOf(Note())
            )
        }
    }

}

private infix fun CreditAccount.incasso(debitAccount: DebitAccount) {
    println(debitAccount)
    println("Money is being payed back!")
}

