package org.jesperancinha.arrow.books.resilience.saga

import arrow.atomic.value
import arrow.core.continuations.AtomicRef
import arrow.resilience.Schedule
import arrow.resilience.transact
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.resilience.saga.LibraryService.Companion.failingTransaction
import org.jesperancinha.arrow.books.resilience.saga.LibraryService.Companion.successfulTransaction
import org.jesperancinha.arrow.books.resilience.saga.LibraryService.Companion.tryFunction
import org.jesperancinha.arrow.books.resilience.saga.LibraryService.Companion.tryFunctionExponentialBackoff
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.milliseconds

class LibraryServiceTest {

    @Test
    fun `should reset counter after transaction fails`(): Unit = runBlocking {
        val initialCounter = ClassBookCounter.books.value
        kotlin.runCatching {
            failingTransaction.transact()
        }
        ClassBookCounter.books.value shouldBe initialCounter
    }

    @Test
    fun `should increase counter after transaction succeeds`(): Unit = runBlocking {
        val initialCounter = ClassBookCounter.books.value
        successfulTransaction.transact()
        ClassBookCounter.books.value shouldBe (initialCounter + 1)

    }

    @Test
    fun `should try-function until successful`(): Unit = runBlocking {
        ClassBookCounter.increment()
        ClassBookCounter.increment()
        ClassBookCounter.increment()
        tryFunction {
            runCatching {
                if (ClassBookCounter.books.get() > 0)
                    println(ClassBookCounter.books)
                ClassBookCounter.decrement()
                throw Exception()
            }
            ClassBookCounter.books.get()
        }
    }

    @Test
    fun `should try-function-exponential backoff until successful`(): Unit = runBlocking {
        ClassBookCounter.setValue(2)
        tryFunctionExponentialBackoff {
            runCatching {
                if (ClassBookCounter.books.get() > 0)
                    println(ClassBookCounter.books)
                ClassBookCounter.decrement()
                throw Exception()
            }
            ClassBookCounter.books.get()
        }
    }

    @Test
    fun `should try-function-exponential backoff literally until successful`(): Unit = runBlocking {
        val first = LocalDateTime.now()
        val timestampHolder = AtomicRef<MutableList<LocalDateTime>>(mutableListOf())
        ClassBookCounter.setValue(5)
        Schedule.exponential<Int>(100.milliseconds)
            .doWhile { input, _ -> input > 0 }
            .repeat {
                timestampHolder.get().add(LocalDateTime.now())
                runCatching {
                    if (ClassBookCounter.books.get() > 0)
                        println("There are still ${ClassBookCounter.books} books on the shelves!")
                    ClassBookCounter.decrement()
                }
                ClassBookCounter.books.get()
            }
        timestampHolder.get().fold(first) { date, acc ->
            println("This try waited ${Duration.between(date, acc).toMillis()} ms to start.")
            date
        }
    }
}