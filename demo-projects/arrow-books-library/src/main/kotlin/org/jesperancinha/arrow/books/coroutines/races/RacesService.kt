package org.jesperancinha.arrow.books.coroutines.races

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.merge
import arrow.core.raise.either
import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parMapOrAccumulate
import arrow.fx.coroutines.parZip
import arrow.fx.coroutines.raceN
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.typed.raise.Book
import org.jesperancinha.arrow.books.printSeparator
import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

class RacesService {
    companion object {
        val randomId
            get() = (1..4).map { Random().nextLong(10) }.joinToString("").toLong()

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            val id = randomId

            printSeparator("Coroutines Test 1.1 - parZip")
            measureTimeMillis {
                println(createBook(id))
            }.run { println("It took $this milliseconds to create a book") }

            runCatching {
                printSeparator("Coroutines Test 1.2 - parZip")
                measureTimeMillis {
                    println(tryTocreateBook(id))
                }.run { println("It took $this milliseconds to create a book") }
            }.onFailure { it }


            printSeparator("Coroutines Test 1.3 - parZip")
            measureTimeMillis {
                println(tryTocreateEitherBook(id))
            }.run { println("It took $this milliseconds to try to create a book") }


            printSeparator("Coroutines Test 2.1 - parMap")
            measureTimeMillis {
                println(getAssociatedTitles(id))
            }.run { println("It took $this milliseconds to read the associates") }

            printSeparator("Coroutines Test 2.2 - parMap Fail")
            measureTimeMillis {
                println(getAssociatedFailTitles(id))
            }.run { println("It took $this milliseconds to read the associates") }

            printSeparator("Coroutines Test 2.3 - parMap cumulative Fail")
            measureTimeMillis {
                println(getAssociatedCumulativeFailTitles(id))
            }.run { println("It took $this milliseconds to read the associates") }

            printSeparator("Coroutines Test 3.1 - raceN")
            println((1..10).map { fetchBook() }.joinToString(","))

            printSeparator("Coroutines Test 3.2 - raceN - Both Fail")
            println((1..10).map { fetchBookFail() }.joinToString(","))
        }

        suspend fun createBook(id: Long): Book =
            parZip(
                {
                    delay(1.seconds)
                    getName(id)
                },
                {
                    delay(1.seconds)
                    getIsdnNumber(id)
                }
            ) { name, isdnNumber -> Book(id = id, name = name, isdnNumber = isdnNumber) }

        suspend fun tryTocreateEitherBook(id: Long)= either {
            parZip(
                {
                    delay(1.seconds)
                    raise("Cannot get name!")
                },
                {
                    delay(1.seconds)
                    getIsdnNumber(id)
                }
            ) { name, isdnNumber -> Book(id = id, name = name, isdnNumber = isdnNumber) }
        }
        suspend fun tryTocreateBook(id: Long): Book =
            parZip(
                {
                    delay(1.seconds)
                    getName(id)
                    throw RuntimeException()
                },
                {
                    delay(1.seconds)
                    getIsdnNumber(id)
                    throw RuntimeException()

                }
            ) { name, isdnNumber -> Book(id = id, name = name, isdnNumber = isdnNumber) }

        fun getName(id: Long) = "Book-$id-${UUID.randomUUID()}".replace("--", "-")

        fun getIsdnNumber(id: Long) =
            (1..5).map { (Random().nextLong(10) + id).toString().last() }.joinToString("").toLong()

        suspend fun getAssociatedTitles(id: Long): List<String> =
            getAssociates(id).parMap {
                delay(1.seconds)
                getName(it)
            }
        suspend fun getAssociatedFailTitles(id: Long): Either<String, List<String>> = either {
            getAssociates(id).parMap {
                delay(1000)
                raise("Cannot get name!")
                getName(it)
            }
        }

        suspend fun getAssociatedCumulativeFailTitles(id: Long): Either<NonEmptyList<String>, List<String>> =
            getAssociates(id).parMapOrAccumulate{
                delay(1000)
                raise("Cannot get name!")
                getName(it)
            }

        private fun getAssociates(id: Long): List<Long> {
            return (1..5).map { Random().nextLong() + id }
        }


        suspend fun fetchBook() =
            raceN(
                { getBookFromLibraryGouda() },
                { getBookFromLibraryOlhao() }
            ).merge()

        suspend fun fetchBookFail() = runCatching {
            raceN(
                {
                    getBookFromLibraryGouda()
                    throw RuntimeException("Major Fail Gouda")
                },
                {
                    getBookFromLibraryOlhao()
                    throw RuntimeException("Major Fail Olhao")
                },
            ).merge()
        }

        suspend fun getBookFromLibraryGouda() = delay(Random().nextLong(100)).run {
            Book(
                id = 1,
                name = "The silence of the kittens",
                isdnNumber = 98765432123456789,
                library = "Gouda"
            )
        }

        suspend fun getBookFromLibraryOlhao() = delay(Random().nextLong(100)).run {
            Book(
                id = 1,
                name = "The silence of the kittens",
                isdnNumber = 98765432123456789,
                library = "Olhao"
            )
        }
    }

}