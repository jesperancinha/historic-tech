package org.jesperancinha.arrow.books

import kotlinx.coroutines.runBlocking
import org.jesperancinha.arrow.books.Design.Companion.suspendProgram
import org.jesperancinha.arrow.books.Design.Companion.suspendProgramFail
import org.junit.jupiter.api.Test

class DesignTest {

    @Test
    fun `should run the design suspend over IO correctly`(): Unit = runBlocking {
        printSeparator("Runs suspend over IO example")
        val processedUserEither = suspendProgram()
        println(processedUserEither)
        println(processedUserEither.getOrNone().getOrNull())
        processedUserEither.fold(
            { error -> println("Error: $error") },
            { value -> println("Success: $value") }
        )
    }

    @Test
    fun `should run the design suspend over IO correctly when it fails`(): Unit = runBlocking {
        printSeparator("Runs suspend over IO example")
        val processedUserEither = suspendProgramFail()
        println(processedUserEither.swap().getOrNull())
        println(processedUserEither.getOrNone().getOrNull())
        processedUserEither.fold(
            { error -> println("Error: $error") },
            { value -> println("Success: $value") }
        )
    }
}