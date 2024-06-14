package org.jesperancinha.arrow.books

import io.kotest.matchers.longs.shouldBeBetween
import io.kotest.matchers.shouldBe
import org.jesperancinha.arrow.books.CollectionsAndFunctions.Companion.memoizedExpensive
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CollectionsAndFunctionsTest {
    @Test
    fun `should perform around 1 second`() {
        measureTimeMillis {
            val result1 = memoizedExpensive(1)
            val result2 = memoizedExpensive(1)
            result1 shouldBe result2
        }.run {
            println("Operation lasted $this milliseconds.")
            this.shouldBeBetween(900, 1100)
        }
    }
}