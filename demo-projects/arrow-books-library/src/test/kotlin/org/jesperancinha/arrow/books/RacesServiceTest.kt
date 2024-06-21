package org.jesperancinha.arrow.books

import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.fetchLetter
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.fetchLetterFail
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.getAssociatedTitles
import org.jesperancinha.arrow.books.coroutines.races.RacesService.Companion.createBook
import org.junit.jupiter.api.Test

class RacesServiceTest {

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
        println(fetchLetter())
    }

    @Test
    fun `should run with Exceptions`() = runBlocking {
        printSeparator("Coroutines Test 2 - raceN Both Fail")
        println(fetchLetterFail())
    }
}