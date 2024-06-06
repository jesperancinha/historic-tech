package org.jesperancinha.asnsei.guessing

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.time.Duration.Companion.milliseconds

class HighTechGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlocking {
        runCatching(AtomicLong(maxAttempts), inputNumber) {
            guessingService.checkIfRandomNumberMatchesInput(inputNumber)
        }
    }
    private suspend fun runCatching(
        atomicAccountRecord: AtomicLong,
        inputNumber: Long,
        f: () -> Any
    ): Result<GameOutput> {
        atomicAccountRecord.decrementAndGet()
        return runCatching {
            f()
        }.onFailure { _ ->
            atomicAccountRecord.takeIf { it.get() == 0L }?.let {
            } ?: delay(delayMillis.milliseconds).also {
                runCatching(atomicAccountRecord, inputNumber, f)
            }
        }.run {
            if (isFailure) {
                Result.failure(
                    GameFailedException(
                        GameOutput.failedAttempts(
                            inputNumber,
                            maxAttempts - atomicAccountRecord.get()
                        )
                    )
                )
            } else {
                Result.success(GameOutput.success(inputNumber, maxAttempts - atomicAccountRecord.get()))
            }
        }
    }

}