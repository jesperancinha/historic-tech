package org.jesperancinha.asnsei.guessing

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.max

class CoroutinesGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlocking {
        val atomicAccountRecord = AtomicLong(1)
        val result = flow {
            emit(guessingService.checkIfRandomNumberMatchesInput(inputNumber))
        }.retry(maxAttempts) { cause ->
            println("Attempt ${atomicAccountRecord.get()} failed, retrying...")
            if(atomicAccountRecord.get() < maxAttempts)
                atomicAccountRecord.incrementAndGet()
            true
        }.catch { _ ->
            println("Failed after ${atomicAccountRecord.get()} attempts")
        }.firstOrNull()

        if (result != null) {
           return@runBlocking Result.success(GameOutput.success(inputNumber,atomicAccountRecord.get()))
        } else {
           return@runBlocking Result.failure<GameOutput>(GameFailedException(GameOutput.failedAttempts(inputNumber, atomicAccountRecord.get())))
        }
    }
}
