package org.jesperancinha.arrow.books

import arrow.core.merge
import arrow.fx.coroutines.parMap
import arrow.fx.coroutines.parZip
import arrow.fx.coroutines.raceN
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

class Coroutines {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) = runBlocking {
            printSeparator("Coroutines Test 1 - parZip")
            println(getClient(1000))
            printSeparator("Coroutines Test 2 - parMap")
            println(getAssociateNames(1000))
            printSeparator("Coroutines Test 2 - raceN")
            println(fetchLetter())
            printSeparator("Coroutines Test 2 - raceN - Both Fail")
            println(fetchLetterFail())

        }

        suspend fun getClient(id: Long): Book =
            parZip(
                { getName(id) },
                { getBookTitle(id) }
            ) { name, isdnNumber -> Book(name = name, isdnNumber =  isdnNumber) }

        fun getName(id: Long) = "client($id)-${UUID.randomUUID()}"
        fun getBookTitle(id: Long) =
            (1..5).map { (Random().nextLong(10) + id).toString().last() }.joinToString("").toLong()

        suspend fun getAssociateNames(id: Long): List<String> =
            getAssociates(id).parMap { getName(it) }

        private fun getAssociates(id: Long): List<Long> {
            return (1..5).map { Random().nextLong() + id }
        }


        suspend fun fetchLetter() =
            raceN(
                { getA() },
                { getB() }
            ).merge()

        suspend fun fetchLetterFail() = runCatching {
            raceN(
                {
                    getA()
                    throw RuntimeException("Major Fail A")
                },
                {
                    getB()
                    throw RuntimeException("Major Fail B")
                },
            ).merge()
        }
        suspend fun getA() = delay(Random().nextLong(100)).run { "A" }
        suspend fun getB() = delay(Random().nextLong(100)).run { "B" }
    }

}