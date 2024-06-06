package org.jesperancinha.asnsei.guessing

import arrow.core.left
import arrow.core.right
import arrow.fx.coroutines.Schedule
import arrow.fx.coroutines.retry
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime


class ArrowGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    @OptIn(ExperimentalTime::class)
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlocking {
        val retryPolicy =
            Schedule.spaced<Throwable>(delayMillis.milliseconds) and Schedule.recurs(maxAttempts.toInt() - 1)
        val atomicAccountRecord = AtomicLong()
        runCatching {
            retryPolicy.retry {
                atomicAccountRecord.incrementAndGet()
                (if (guessingService.checkIfRandomNumberMatchesAssertion(inputNumber)) {
                    "Success".right()
                } else {
                    Exception("Operation failed").also {
                        println("Attempt ${atomicAccountRecord.get()} failed, retrying...")
                    }.left()
                }).fold({ throw it }, { it })
            }
        }.run {
            if (isSuccess) {
                Result.success(GameOutput.success(inputNumber, atomicAccountRecord.get()))
            } else {
                Result.failure(
                    GameFailedException(
                        GameOutput.failedAttempts(
                            inputNumber,
                            atomicAccountRecord.get()
                        )
                    )
                )
            }
        }

    }

}