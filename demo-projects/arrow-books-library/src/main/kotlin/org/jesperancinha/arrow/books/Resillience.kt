package org.jesperancinha.arrow.books

import arrow.atomic.AtomicInt
import arrow.resilience.Saga
import arrow.resilience.Schedule
import arrow.resilience.saga
import arrow.resilience.transact
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

val INITIAL_VALUE = 1

object Counter {
    val value = AtomicInt(INITIAL_VALUE)

    fun increment() {
        value.incrementAndGet()
    }

    fun decrement() {
        value.decrementAndGet()
    }
}

val PROBLEM = Throwable("problem detected!")

class Resillience {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runBlocking {
                printSeparator("Resillience Example 1 - Saga Fail")
                val transactionFail: Saga<Int> = failingTransaction
                println(transactionFail)
                runCatching {
                    println(transactionFail.transact())
                }.onFailure { println(it) }
                println(Counter.value)
                printSeparator("Resillience Example 2 - Saga Success")
                val transaction: Saga<Int> = successfulTransaction
                println(transaction)
                println(transaction.transact())
                println(Counter.value)
                printSeparator("Resillience Example 3 - Retry and Repeat")
                val result = tryFunction {
                    runCatching {
                        if (Counter.value.get() > 0)
                            println(Counter.value)
                        Counter.decrement()
                        throw Exception()
                    }
                    Counter.value.get()
                }
                println(result)
            }
        }

        suspend fun tryFunction(f: () -> Int) = Schedule.doWhile<Int> { input, _ -> input > 0 }.repeat {
            f()
        }
        suspend fun tryFunctionExponentialBackoff(f: () -> Int) = Schedule.exponential<Int>(1.seconds).doWhile { input, _ -> input > 0 }.repeat {
            f()
        }

        val failingTransaction = saga {
            saga({
                Counter.increment()
            }) {
                Counter.decrement()
            }
            saga({
                throw PROBLEM
            }) {}
        }
        val successfulTransaction = saga {
            saga({
                Counter.increment()
            }) {
                Counter.decrement()
            }
            saga({
                println("No problem here!")
            }) {}
            Counter.value.get()
        }
    }
}