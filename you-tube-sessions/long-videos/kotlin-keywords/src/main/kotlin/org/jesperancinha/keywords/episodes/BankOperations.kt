package org.jesperancinha.keywords.episodes

import java.time.LocalDate

internal class MasterDebitCardIssuer {
    private val created = LocalDate.now()

    inner class MasterDebitCard(account: DebitAccount) : Card<DebitAccount, Note>(account) {
        fun winPrize() = if (LocalDate.now().dayOfMonth == created.dayOfMonth) {
            account.add(Note())
        } else account
    }
}


class Bank(
    val ownedCards: List<Card<Account, Coin>>
) {
    init {
        logger.info("This bank has been initialized!")
    }
}

/**
 * infix
 * inner
 * private, protected, internal, public
 * by
 * lateinit
 */
class BankOperations {

    val bank by lazy { Bank(listOf()) }

    lateinit var anotherBank: Bank

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runInfixExample()
            runInnerExample()
            runByExample()
            runLateInitVarExample()
        }

        private fun runLateInitVarExample() {
            val bankOperations = BankOperations()
            runCatching {
                logger.info("${bankOperations.anotherBank}")
            }.onFailure {
                logger.error("This fails because although it compiles, we never assigned it a value")
                logger.error("It cannot also have a null value")
            }
            logger.info("If we assign it now")
            bankOperations.anotherBank = Bank(listOf())
            logger.info("${bankOperations.anotherBank}")
            logger.info("It works now because the variable is assigned.")
        }

        private fun runByExample() {
            logger.info("We create this episode and nothing happens!")
            val bankOperations = BankOperations()
            logger.info("$bankOperations")
            logger.info("We now just access the bank!")
            bankOperations.bank
            logger.info("Works like a charm!")
        }

        private fun runInnerExample() {
            val masterDebitCardIssuer = MasterDebitCardIssuer()
            val masterDebitCard = masterDebitCardIssuer.MasterDebitCard(
                DebitAccount(
                    name = "The bravado",
                    moneyCollection = listOf(Note())
                )
            )
            val masterDebitCardWinner = masterDebitCard.winPrize()
        }

        private fun runInfixExample() {
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
    logger.info("${debitAccount}")
    logger.info("Money is being payed back!")
}

