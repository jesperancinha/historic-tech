package org.jesperancinha.asnsei.guessing


abstract class Retry(
    val guessingService: NumberGeneratorService
) {

    val maxAttempts = 3
    val delayMillis = 100L

    abstract fun checkNumber(inputNumber: Long): Result<GameOutput>
}

data class GameOutput(
    val success: Boolean,
    val ex: Exception? = null,
    val message: String
)

fun main() {
    val traditionalGame = TraditionalRetry(NumberGeneratorService())
    val checkNumberResult = traditionalGame.checkNumber(10)
    println(checkNumberResult)

}