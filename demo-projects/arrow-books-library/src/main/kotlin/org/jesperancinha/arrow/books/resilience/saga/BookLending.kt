package org.jesperancinha.arrow.books.resilience.saga

import arrow.resilience.saga
import org.jesperancinha.arrow.books.typed.raise.Book

data class User(
    val id: Int,
    val name: String,
    val bookCount: Int = 0
)

data class ReserveTicket(
    val id: Int = -1,
    val book: Book,
    val user: User
)

interface Repository<T, ID> {
    fun findById(id: ID): T
    fun findByIdOrNull(id: ID): T?
    fun save(t: T): T
    fun delete(t: T)
}

class BookRepository() : Repository<Book, Int> {
    override fun findByIdOrNull(id: Int): Book? = databaseBooks[id]
    override fun findById(id: Int): Book = requireNotNull(databaseBooks[id])
    override fun save(book: Book): Book = run {
        databaseBooks.put(book.id, book)
        book
    }

    override fun delete(t: Book) {
        TODO("Not yet implemented")
    }
}

open class ReservationsRepository : Repository<ReserveTicket, Int> {
    override fun findById(id: Int) = requireNotNull(reservations[id])
    override fun findByIdOrNull(id: Int) = reservations[id]
    override fun save(reserveTicket: ReserveTicket) = run {
        reservations.put(reserveTicket.id, reserveTicket)
        reserveTicket
    }

    override fun delete(reserveTicket: ReserveTicket) {
        reservations.remove(reserveTicket.id)
    }
}

class UserRepository : Repository<User, Int> {
    override fun findById(id: Int) = requireNotNull(users[id])
    override fun findByIdOrNull(id: Int) = users[id]
    override fun save(user: User) = run {
        users.put(user.id, user)
        user
    }

    override fun delete(t: User) {
        TODO("Not yet implemented")
    }
}

class BookService(
    val bookRepository: BookRepository,
    val reservationsRepository: ReservationsRepository,
    val userRepository: UserRepository
) {
    fun pickBook(id: Int) = bookRepository.findById(id)
        .let { bookRepository.save(it.copy(inShelf = false)) }

    fun restoreBook(id: Int) = bookRepository.findById(id)
        .let { bookRepository.save(it.copy(inShelf = true)) }

    fun reserveBook(book: Book, user: User) = ReserveTicket(
        book = book,
        user = userRepository.save(user.copy(bookCount = user.bookCount + 1)),
    )

    fun returnReservation(user: User) = run {
        val findById = userRepository.findById(user.id)
        userRepository.save(findById.copy(bookCount = findById.bookCount - 1))
    }

    fun registerReservation(reserveTicket: ReserveTicket) =
        if (reserveTicket.id == -1) {
            reservationsRepository.save(reserveTicket.copy(reservations.keys.maxOrNull() ?: 0 + 1))
        } else {
            reservationsRepository.save(reserveTicket)
        }

    fun unRegisterReservation(reserveTicket: ReserveTicket) = reservationsRepository.delete(reserveTicket)

    fun getABookWithReceipt(id: Int, user: User) = saga {
        saga({
            pickBook(id)
        }) {
            restoreBook(id)
        }
        val reserveTicket = saga({
            reserveBook(bookRepository.findById(id), user)
        }) {
            returnReservation(user)
        }
        saga({
            registerReservation(reserveTicket)
        }) {
            unRegisterReservation(reserveTicket)
        }
    }

    companion object {
        operator fun invoke() = BookService(BookRepository(), ReservationsRepository(), UserRepository())
    }
}
