package org.jesperancinha

import org.jesperancinha.Main.Companion.fibonacciRecursive


class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println(fibonacciIterative(100))
            println(fibonacciTailRecursiveTCO(100))
            println(fibonacciTailRecursive(100))
        }

        private fun fibonacciIterative(n: Int): Double {
            if (n <= 1) {
                return n.toDouble()
            }

            var a = 0.0
            var b = 1.0

            for (i in 2..n) {
                val temp = a + b
                a = b
                b = temp
            }

            return b
        }

        fun fibonacciRecursive(n: Int): Double =
            if (n <= 1) n.toDouble()
            else
                fibonacciRecursive(n - 1) +
                        fibonacciRecursive(n - 2)

        private tailrec fun fibonacciTailRecursiveTCO(
            n: Int,
            a: Double = 0.0,
            b: Double = 1.0
        ): Double = if (n == 0) a
        else fibonacciTailRecursiveTCO(n - 1, b, a + b)

        private fun fibonacciTailRecursive(
            n: Int,
            a: Double = 0.0,
            b: Double = 1.0
        ): Double = if (n == 0) a
        else fibonacciTailRecursive(n - 1, b, a + b)
    }
}


class MainSlow {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(fibonacciRecursive(100))
        }
    }
}