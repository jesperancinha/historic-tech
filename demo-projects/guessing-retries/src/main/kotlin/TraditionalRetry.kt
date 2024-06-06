package org.jesperancinha.asnsei.guessing

class TraditionalRetry(guessingService: NumberGeneratorService) : Retry(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runCatching {
        var attempts = 0
        while (attempts < maxAttempts) {
            try {
                guessingService.checkIfRandomNumberMatchesInput(inputNumber)
                GameOutput(
                    success = true,
                    message = "Your number $inputNumber matches one of the generated ones after $attempts! Congratulations!"
                )
            } catch (e: Exception) {
                attempts++
                if (attempts >= maxAttempts) {
                    println("Failed after $attempts attempts")
                } else {
                    println("Attempt $attempts failed, retrying...")
                    Thread.sleep(delayMillis)
                }
            }
        }

        throw GameFailedException(
            GameOutput(
                success = false,
                ex = RuntimeException("Attempts failed!"),
                message = "Your number didn't match any of the $attempts generated values"
            )
        )
    }

}