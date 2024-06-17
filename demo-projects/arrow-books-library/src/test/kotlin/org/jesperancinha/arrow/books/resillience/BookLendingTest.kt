package org.jesperancinha.arrow.books.resillience

import arrow.resilience.transact
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class BookLendingTest {
    val bookService by lazy { BookService() }

    @Test
    fun `should create reservation for book 1`(): Unit = runBlocking {
        val user = UserRepository().findById(1)
        val receipt = bookService.getABookWithReceipt(1, user).transact()

        receipt.book.inShelf.shouldNotBeTrue()
        receipt.book shouldBe databaseBooks[1]
        receipt.user.name shouldBe users[1]?.name
        receipt.user.bookCount shouldBe 1

        bookService.restoreBook(1)
        bookService.unRegisterReservation(receipt)
        bookService.returnReservation(user)
    }

    @Test
    fun `should create reservation but fail for book 1`(): Unit = runBlocking {
        val user = UserRepository().findById(1)
        runCatching {
            val receipt = BookService(
                BookRepository(),
                object : ReservationsRepository() {
                    override fun save(reserveTicket: ReserveTicket): ReserveTicket {
                        throw RuntimeException("Saving reservation failed!")
                    }
                },
                UserRepository()
            ).getABookWithReceipt(1, user).transact()
        }
        BookRepository().findById(1).inShelf.shouldBeTrue()
        UserRepository().findById(1).bookCount shouldBe 0
        ReservationsRepository().findByIdOrNull(1).shouldBeNull()
    }
}