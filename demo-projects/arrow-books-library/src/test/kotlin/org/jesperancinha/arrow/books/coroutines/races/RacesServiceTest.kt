package org.jesperancinha.arrow.books.coroutines.races

import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.fetchBook
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.fetchBookFail
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.getAssociatedTitles
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.createBook
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.fetchBookEitherFail
import org.jesperancinha.arrow.books.printSeparator
import org.junit.jupiter.api.Test

class RacesServiceTest {


    @Test
    fun `should Coroutines Test 3_3 - raceN - Both Either Fail`() = runBlocking{
        printSeparator("Coroutines Test 3.3 - raceN - Both Either Fail")
        println((1..10).map { fetchBookEitherFail() }.joinToString(","))
    }

    @Test
    fun `should run parZip`() = runBlocking {
        printSeparator("Coroutines Test 1 - parZip")
        println(createBook(1000))

    }

    @Test
    fun `should run parMap`() = runBlocking {
        printSeparator("Coroutines Test 2 - parMap")
        println(getAssociatedTitles(1000))
    }

    @Test
    fun `should run raceN`() = runBlocking {
        printSeparator("Coroutines Test 2 - raceN")
        println(fetchBook())
    }

    @Test
    fun `should run with Exceptions`() = runBlocking {
        printSeparator("Coroutines Test 2 - raceN Both Fail")
        println(fetchBookFail())
    }
}