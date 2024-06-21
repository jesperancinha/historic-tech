package org.jesperancinha.arrow.books.coroutines.races

import arrow.core.merge
import arrow.fx.coroutines.parMap
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
            printSeparator("Coroutines Test 1 - parZip")

            measureTimeMillis {
                println(createBook(id))
            }.run { println("It took $this seconds to read the client") }

            printSeparator("Coroutines Test 2 - parMap")
            measureTimeMillis {
                println(getAssociateNames(id))
            }.run { println("It took $this seconds to read the associates") }

            printSeparator("Coroutines Test 3 - raceN")
            println((1..10).map { fetchLetter() }.joinToString(","))

            printSeparator("Coroutines Test 4 - raceN - Both Fail")
            println((1..10).map { fetchLetterFail() }.joinToString(","))
        }

        suspend fun createBook(id: Long): Book =
            parZip(
                {
                    delay(1.seconds)
                    getName(id)
                },
                {
                    delay(1.seconds)
                    getBookTitle(id)
                }
            ) { name, isdnNumber -> Book(id = id, name = name, isdnNumber = isdnNumber) }

        fun getName(id: Long) = "Book-$id-${UUID.randomUUID()}".replace("--", "-")

        fun getBookTitle(id: Long) =
            (1..5).map { (Random().nextLong(10) + id).toString().last() }.joinToString("").toLong()

        suspend fun getAssociateNames(id: Long): List<String> =
            getAssociates(id).parMap { getName(it) }

        private fun getAssociates(id: Long): List<Long> {
            return (1..5).map { Random().nextLong() + id }
        }


        suspend fun fetchLetter() =
            raceN(
                { getBookFromLibraryGouda() },
                { getBookFromLibraryOlhao() }
            ).merge()

        suspend fun fetchLetterFail() = runCatching {
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
                library = "Solothurn"
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