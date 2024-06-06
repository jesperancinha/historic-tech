package org.jesperancinha.asnsei.guessing

import arrow.core.Either
import arrow.fx.coroutines.Schedule
import arrow.fx.coroutines.retry
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


class ArrowGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    @OptIn(ExperimentalTime::class)
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlocking {
        val retryPolicy = Schedule.recurs<Throwable>(5) andThen Schedule.spaced(1.seconds)
        val atomicAccountRecord = AtomicLong()
        val result = retryPolicy.retry {
            atomicAccountRecord.incrementAndGet()
            guessingService.checkIfRandomNumberMatchesInput(inputNumber)
            if (guessingService.checkIfRandomNumberMatchesAssertion(inputNumber)) {
                Either.Left(Exception("Operation failed")).also {
                    println("Attempt ${atomicAccountRecord.get()} failed, retrying...")
                    delay(delayMillis)
                }
            } else {
                Either.Right("Success")
            }
        }

        result.fold(
            {
                println("Operation failed after maximum attempts: ${it.message}")
                Result.success(GameOutput.success(inputNumber, atomicAccountRecord.get()))
            },
            {
                println("Operation succeeded with result: $it")
                Result.failure(
                    GameFailedException(
                        GameOutput.failedAttempts(
                            inputNumber,
                            atomicAccountRecord.get()
                        )
                    )
                )
            }
        )
    }

}