package org.jesperancinha.asnsei.guessing

class TraditionalGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runCatching {
        var attempts = 1L
        while (attempts < maxAttempts) {
            val result = runCatching {
                guessingService.checkIfRandomNumberMatchesInput(inputNumber)
                GameOutput.success(inputNumber, attempts)
            }.onFailure {
                attempts++
                if (attempts == maxAttempts) {
                    println("Failed after $attempts attempts")
                } else {
                    println("Attempt $attempts failed, retrying...")
                    Thread.sleep(delayMillis)
                }
            }
            if (result.isFailure && attempts == 0L) {
                throw GameFailedException(
                    GameOutput.failedAttempts(inputNumber, attempts)
                )
            }
            if (attempts < maxAttempts && result.isSuccess) {
                return result
            }
        }
        throw GameFailedException(
            GameOutput.failedAlgorithm(attempts)
        )
    }
}