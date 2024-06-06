package org.jesperancinha.asnsei.guessing


import java.util.*

data class GameFailedException(val gameResult: GameOutput) : RuntimeException()

open class CommonGuessingGameService {
    private fun createRandomNumber() = Random().nextLong()
    open fun assertResult(inputNumber: Long) = inputNumber == createRandomNumber()
    open fun checkIfRandomNumberMatchesInput(inputNumber: Long) =
        (checkIfRandomNumberMatchesAssertion(inputNumber)).takeIf { it } ?: throw RuntimeException("Failed Attempt")
    open fun checkIfRandomNumberMatchesAssertion(inputNumber: Long) = assertResult(inputNumber)
}
