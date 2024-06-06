package org.jesperancinha.asnsei.guessing


abstract class Retry<T>(
    val guessingService: CommonGuessingGameService
) {

    val maxAttempts = 3L
    val delayMillis = 100L

    abstract fun checkNumber(inputNumber: Long): T
}

data class GameOutput(
    val success: Boolean,
    val ex: Exception? = null,
    val message: String,
    val retries: Long
) {

    companion object {
        fun success(inputNumber: Long, attempts: Long) = GameOutput(
            success = true,
            message = "Your number $inputNumber matches one of the generated ones after $attempts! Congratulations!",
            retries = attempts
        )

        fun failedAttempts(inputNumber: Long, attempts: Long) = GameOutput(
            success = false,
            ex = RuntimeException("Attempts failed!"),
            message = "Your number $inputNumber didn't match any of the $attempts generated values",
            retries = attempts
        )

        fun failedAlgorithm(attempts: Long) = GameOutput(
            success = false,
            ex = RuntimeException("Algorithm Failure!"),
            message = "There has been system fault! This shouldn't have happened!",
            retries = attempts
        )
    }
}

fun main() {
    val traditionalGame = TraditionalGameRetry(CommonGuessingGameService())
    val checkNumberResult = traditionalGame.checkNumber(10)
    println(checkNumberResult)

}