package org.jesperancinha.arrow.books

import arrow.core.Either
import arrow.core.raise.either
import kotlinx.coroutines.runBlocking

object PersistenceError
data class ProcessedUser(val id: Id, val email: String, val name: String)
class Id(val id: Long)
data class Gebruiker(val email: String, val name: String)


class Design {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runBlocking {
                suspendProgram()
                suspendProgramFail()
            }
        }

        suspend fun suspendProgram(): Either<PersistenceError, ProcessedUser> =
            either {
                val user = fetchUser().bind()
                val processed = user.process().bind()
                processed
            }
        suspend fun suspendProgramFail(): Either<PersistenceError, ProcessedUser> =
            either {
                val user = fetchUserFail().bind()
                val processed = user.process().bind()
                processed
            }

        suspend fun fetchUser(): Either<PersistenceError, Gebruiker> =
            Either.Right(Gebruiker("raccoon-rocket@arrow-kt.io", "Rocket from a planet far far aways"))

        suspend fun fetchUserFail(): Either<PersistenceError, Gebruiker> =
            Either.Right(Gebruiker("raccoon-rocket-arrow-kt.io", "Rocket from a planet far far aways"))

        suspend fun Gebruiker.process(): Either<PersistenceError, ProcessedUser> =
            if (email.contains(Regex("^(.+)@(.+)$"))) Either.Right(ProcessedUser(Id(1), email, name))
            else Either.Left(PersistenceError)

    }
}