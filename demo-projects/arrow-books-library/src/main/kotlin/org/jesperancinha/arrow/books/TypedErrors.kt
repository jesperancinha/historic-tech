package org.jesperancinha.arrow.books

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.withError
import arrow.core.right
import java.util.*


data class BookNotFound(val message: String? = null)
data class Book(
    val id: Int = -1,
    val name: String = "client${UUID.randomUUID()}",
    val isdnNumber: Long,
    val inShelf: Boolean = true
)


fun Raise<BookNotFound>.user(): Book = Book(isdnNumber = 1)
fun Raise<BookNotFound>.error(): Book = raise(BookNotFound())

fun Book.isValid(): Either<BookNotFound, Unit> = either {
    ensure(isdnNumber > 0) { BookNotFound("A book can only be a positive number!!!!: $isdnNumber") }
}

fun Raise<BookNotFound>.isValid(book: Book): Book {
    ensure(book.isdnNumber > 0) { BookNotFound("book without a valid number: ${book.isdnNumber}") }
    return book
}

//

class Thing(val text: String)
class Boo

fun Raise<Error>.f(n: Int): String = "F"
fun Raise<Error>.g(s: String): Thing = Thing("G")
fun Raise<Boo>.h(s: String): Thing = Thing("H")
fun Thing.summarize(): String = "SUMMARY"

fun Raise<Error>.bar(n: Int): String {
    val s = f(n)
    val t = withError({ boo -> Error() }) { h(s) }
    return t.summarize()
}

class TypedErrors {

    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val book: Either<BookNotFound, Book> = Book(isdnNumber = 1).right()
            val error: Either<BookNotFound, Book> = BookNotFound().left()

            printSeparator("book")
            println(book)
            println(book.getOrNone())
            println(book.getOrNone().getOrNull())
            println(book.map { it.isdnNumber })
            printSeparator("book Number Error")
            println(error)
            println(error.getOrNone())

            printSeparator("Raise example")
            val text = either {
                bar(10)
            }
            println(text.getOrNone().getOrNull())
        }
    }
}