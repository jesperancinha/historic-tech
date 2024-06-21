package org.jesperancinha.arrow.books

import arrow.core.left
import arrow.core.raise.fold
import io.kotest.matchers.shouldBe
import org.jesperancinha.arrow.books.typed.raise.Book
import org.jesperancinha.arrow.books.typed.raise.BookNotFound
import org.jesperancinha.arrow.books.typed.raise.isValid
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class BooksWithErrorsServiceTest {

    @Test
    fun `should make tests about example 1`() {
        Book(isdnNumber = -1).isValid() shouldBe BookNotFound("A book can only be a positive number!!!!: -1").left()

        fold(
            { isValid(Book(isdnNumber = 1)) },
            { _: BookNotFound -> fail("No logical failure occurred!") },
            { book: Book -> book.isdnNumber shouldBe 1 }
        )
    }
}