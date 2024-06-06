package org.jesperancinha.asnsei.guessing

import io.github.resilience4j.kotlin.retry.executeSuspendFunction
import io.github.resilience4j.retry.RetryConfig
import io.github.resilience4j.retry.RetryRegistry
import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.util.concurrent.atomic.AtomicLong

class Resilience4JGameRetry(guessingService: CommonGuessingGameService) :
    Retry<Result<GameOutput>>(guessingService) {
    val atomicAccountRecord = AtomicLong()
    override fun checkNumber(inputNumber: Long): Result<GameOutput> {
        val retryConfig = RetryConfig.custom<String>()
            .maxAttempts(3)
            .waitDuration(Duration.ofSeconds(1))
            .retryExceptions(Exception::class.java)
            .build()
        val retryRegistry = RetryRegistry.of(retryConfig)
        val retry = retryRegistry.retry("retryExample")
        val gameOutput = runBlocking {
            runCatching {
                val result = retry.executeSuspendFunction {
                    atomicAccountRecord.incrementAndGet()
                    guessingService.checkIfRandomNumberMatchesInput(inputNumber)
                }
            }.takeIf { it.isSuccess }
                ?.run { Result.success(GameOutput.success(inputNumber, atomicAccountRecord.get())) }
                ?: Result.failure(
                    GameFailedException(
                        GameOutput.failedAttempts(
                            inputNumber,
                            atomicAccountRecord.get()
                        )
                    )
                )
        }
        return gameOutput
    }
}