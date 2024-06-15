package org.jesperancinha.arrow.books

import io.kotest.matchers.longs.shouldBeBetween
import io.kotest.matchers.shouldBe
import org.jesperancinha.arrow.books.CollectionsAndFunctions.Companion.memoizeSquarerootOf
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CollectionsAndFunctionsTest {
    @Test
    fun `should perform in around 2 seconds`() {
        measureTimeMillis {
            val result1 = memoizeSquarerootOf(1764)
            val result2 = memoizeSquarerootOf(1764)
            val result3 = memoizeSquarerootOf(
                memoizeSquarerootOf(3111696)
            )
            println(result1)
            println(result2)
            println(result3)
            result1 shouldBe 42
            result2 shouldBe 42
            result3 shouldBe 42
        }.run {
            println("Operation lasted $this milliseconds.")
            this.shouldBeBetween(1900, 2100)
        }
    }
}