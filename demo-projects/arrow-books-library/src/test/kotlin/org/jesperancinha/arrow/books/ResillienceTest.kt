package org.jesperancinha.arrow.books

import arrow.atomic.value
import arrow.resilience.transact
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.Resillience.Companion.failingTransaction
import org.jesperancinha.arrow.books.Resillience.Companion.successfulTransaction
import org.jesperancinha.arrow.books.Resillience.Companion.tryFunction
import org.jesperancinha.arrow.books.Resillience.Companion.tryFunctionExponentialBackoff
import org.junit.jupiter.api.Test

class ResillienceTest {

    @Test
    fun `should reset counter after transaction fails`(): Unit = runBlocking {
        val initialCounter = Counter.value.value
        kotlin.runCatching {
            failingTransaction.transact()
        }
        Counter.value.value shouldBe initialCounter
    }

    @Test
    fun `should increase counter after transaction succeeds`(): Unit = runBlocking {
        val initialCounter = Counter.value.value
        successfulTransaction.transact()
        Counter.value.value shouldBe (initialCounter + 1)

    }

    @Test
    fun `should try-function until successful`(): Unit = runBlocking {
        Counter.increment()
        Counter.increment()
        Counter.increment()
        tryFunction {
            runCatching {
                if (Counter.value.get() > 0)
                    println(Counter.value)
                Counter.decrement()
                throw Exception()
            }
            Counter.value.get()
        }
    }

    @Test
    fun `should try-function-exponential backoff until successful`(): Unit = runBlocking {
        Counter.increment()
        Counter.increment()
        Counter.increment()
        tryFunctionExponentialBackoff {
            runCatching {
                if (Counter.value.get() > 0)
                    println(Counter.value)
                Counter.decrement()
                throw Exception()
            }
            Counter.value.get()
        }
    }
}