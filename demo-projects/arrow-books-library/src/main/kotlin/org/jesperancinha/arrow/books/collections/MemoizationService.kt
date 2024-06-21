package org.jesperancinha.arrow.books.collections

import arrow.core.memoize
import kotlin.math.sqrt

class MemoizationService {
    companion object {
        fun squareRootOf(input: Int): Int {
            val result = sqrt(input.toDouble()).toInt()
            Thread.sleep(1000L)
            return result
        }

        val memoizeSquarerootOf = Companion::squareRootOf.memoize()

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val result1 = memoizeSquarerootOf(1764)
            val result2 = memoizeSquarerootOf(1764)
            val result3 = memoizeSquarerootOf(memoizeSquarerootOf(3111696))
            println(result1)
            println(result2)
            println(result3)
        }
    }
}