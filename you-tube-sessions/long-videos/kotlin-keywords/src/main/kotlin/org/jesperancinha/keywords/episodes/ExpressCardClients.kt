package org.jesperancinha.keywords.episodes


annotation class GoldenCard

@GoldenCard
class ExpressCard<A : Account, M : Money>(account: A) : Card<A, M>(account)

/**
 * Multiplatform (please check kotlin-keywords-app for details)
 * expect
 * actual
 *
 * Native
 * external
 *
 * annotation
 * crossinline
 * inline
 * noinline
 * companion
 * Invokes
 * vararg
 */
class ExpressCardClients {

    companion object {

        operator fun invoke() {
            logger.info("One ExpressCardClients has been created with invoke!")
        }
        operator fun invoke(message:String) {
            logger.info("One ExpressCardClients has been created with invoke and a message: \"{}\"", message)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            ExpressCardClients()
            invoke()
            ExpressCardClients("Please make some soup")
            val expressCardA: ExpressCard<DebitAccount, Note> =
                ExpressCard(DebitAccount("Express Card Holder A", listOf(Note())))

            val expressCardB: ExpressCard<DebitAccount, Note> =
                ExpressCard(DebitAccount("Express Card Holder B", listOf(Note())))

            attendClients("A","B")
            val clients = arrayOf("A", "B")
            attendClients(*clients)
            processCard {
                println("Processing Card A")
                println(expressCardA)
            }
            processCard {
                println("Processing Card B")
                println(expressCardB)
            }
            removeCard {
                println("Removing Card A")
                println(expressCardA)
            }
            removeCard {
                println("Removing Card B")
                println(expressCardB)
            }
            transferCard {
                println("Transferring Card A")
                println(expressCardA)
            }
            transferCard {
                println("Transferring Card B")
                println(expressCardB)
            }
        }
    }
}


/**
 * We cannot use crossinline or noinline in regular parameters.
 * It in only allowed for function parameters
 */
inline fun attendClients(vararg clients: String)  = println("Starting to attend clients ${clients.joinToString(",")}}")
/**
 * Note that this function is identified by IntelliJ as leading to performance issues
 */
inline fun removeCard(noinline cardProcess: () -> Unit): () -> Unit {
    val f = Runnable { cardProcess() }
    f.run()
    return cardProcess
}

/**
 * Cross in line is needed here
 */
inline fun processCard(crossinline cardProcess: () -> Unit) {
    val f = Runnable { cardProcess() }
    f.run()
    // Won't compile. It's crossinline
    // return cardProcess
}

fun transferCard( cardProcess: () -> Unit): () -> Unit {
    val f = Runnable { cardProcess() }
    f.run()
    return cardProcess
}