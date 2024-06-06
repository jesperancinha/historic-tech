package org.jesperancinha.asnsei.guessing


import java.util.Random

data class GameFailedException(val gameResult: GameOutput) : RuntimeException()

open class CommonGuessingGameService {
    private fun createRandomNumber() = Random().nextLong()
    open fun assertResult(inputNumber: Long) = inputNumber == createRandomNumber()
    open fun checkIfRandomNumberMatchesInput(inputNumber: Long) =
        (assertResult(inputNumber)).takeIf { it } ?: throw RuntimeException("Failed Attempt")
}
