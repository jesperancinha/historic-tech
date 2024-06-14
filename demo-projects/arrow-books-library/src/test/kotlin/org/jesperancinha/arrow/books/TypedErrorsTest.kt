package org.jesperancinha.arrow.books

import arrow.core.left
import arrow.core.raise.fold
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class TypedErrorsTest {

    @Test
    fun `should make tests about example 1`() {
        Book(isdnNumber = -1).isValid() shouldBe BookNotFound("A telephone can only be a positive number!!!!: -1").left()

        fold(
            { isValid(Book(isdnNumber = 1)) },
            { _: BookNotFound -> fail("No logical failure occurred!") },
            { book: Book -> book.isdnNumber shouldBe 1 }
        )
    }
}