package org.jesperancinha.arrow.books

import arrow.atomic.AtomicInt
import arrow.resilience.Saga
import arrow.resilience.Schedule
import arrow.resilience.saga
import arrow.resilience.transact
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

val N_SHELVED_BOOKS = 10

object ClassBookCounter {
    val books = AtomicInt(N_SHELVED_BOOKS)

    fun increment() {
        books.incrementAndGet()
    }

    fun decrement() {
        books.decrementAndGet()
    }

    fun setValue (books: Int) {
        this.books.set(books)
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
                println(ClassBookCounter.books)
                printSeparator("Resillience Example 2 - Saga Success")
                val transaction: Saga<Int> = successfulTransaction
                println(transaction)
                println(transaction.transact())
                println(ClassBookCounter.books)
                printSeparator("Resillience Example 3 - Retry and Repeat")
                val result = tryFunction {
                    runCatching {
                        if (ClassBookCounter.books.get() > 0)
                            println(ClassBookCounter.books)
                        ClassBookCounter.decrement()
                        throw Exception()
                    }
                    ClassBookCounter.books.get()
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
                ClassBookCounter.increment()
            }) {
                ClassBookCounter.decrement()
            }
            saga({
                throw PROBLEM
            }) {}
        }
        val successfulTransaction = saga {
            saga({
                ClassBookCounter.increment()
            }) {
                ClassBookCounter.decrement()
            }
            saga({
                println("No problem here!")
            }) {}
            ClassBookCounter.books.get()
        }
    }
}