package org.jesperancinha.keywords

import org.jesperancinha.keywords.episodes.*

/**
 * None of these classes are possible to create, because they are declared as being subclasses of sealed class BonusCard and that means that the subclasses need to be in the same module and in the same package.
 * This is because the sealed class is only known at compile time. Once compiled, there is no way to create subclasses of the sealed class.
 */
//class ImpossibleCard : BonusCard() {
//    class AlsoNotPossibleCard : BonusCard()
//}


/**
 * inline
 * in
 * out
 * this
 * init
 * receiver

 * actual
 * annotation
 * crossinline
 * companion
 * external
 * expect
 * infix
 * inline
 * inner
 * internal
 * lateinit
 * noinline
 * open
 * operator
 * reified
 * sealed
 * suspend
 * tailrec
 * vararg
 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    CardsAndMortgages.main(emptyArray())
    CardsAndMortgagesJava.main(emptyArray())
    ExpressCardClients.main(emptyArray())
    BankOperations.main(emptyArray())
    TechnicalBanking.main(emptyArray())



}