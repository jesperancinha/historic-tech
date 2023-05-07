package org.jesperancinha.keywords.episodes


annotation class GoldenCard


@GoldenCard
class ExpressCard<A : Account, M : Money>(account: A) : Card<A, M>(account)

/**
 * Multiplatform
 * actual
 * expect
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
 */
class Episode2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val expressCard: ExpressCard<DebitAccount, Note>
        }
    }
}