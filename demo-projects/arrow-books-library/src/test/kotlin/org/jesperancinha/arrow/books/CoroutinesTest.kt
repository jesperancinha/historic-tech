package org.jesperancinha.arrow.books

import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.Coroutines.Companion.fetchLetter
import org.jesperancinha.arrow.books.Coroutines.Companion.fetchLetterFail
import org.jesperancinha.arrow.books.Coroutines.Companion.getAssociateNames
import org.jesperancinha.arrow.books.Coroutines.Companion.getClient
import org.junit.jupiter.api.Test

class CoroutinesTest {

    @Test
    fun `should run parZip`() = runBlocking {
        printSeparator("Coroutines Test 1 - parZip")
        println(getClient(1000))

    }

    @Test
    fun `should run parMap`() = runBlocking {
        printSeparator("Coroutines Test 2 - parMap")
        println(getAssociateNames(1000))
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