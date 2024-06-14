package org.jesperancinha.arrow.books

import arrow.core.memoize

class CollectionsAndFunctions {
    companion object {
        fun expensive(x: Int): Int {
            Thread.sleep(x * 1000L)
            return x
        }

        val memoizedExpensive = ::expensive.memoize()

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val result1 = memoizedExpensive(3)
            val result2 = memoizedExpensive(3)
            println(result1)
            println(result2)
        }
    }
}