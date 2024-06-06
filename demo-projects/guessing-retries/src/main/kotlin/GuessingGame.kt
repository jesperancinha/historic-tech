package org.jesperancinha.asnsei.guessing



import java.util.Random

data class GameFailedException(val gameResult: GameOutput): RuntimeException()

class NumberGeneratorService {
    private fun createRandomNumber() = Random().nextLong()
    fun checkIfRandomNumberMatchesInput(inputNumber: Long) =
        (inputNumber == createRandomNumber()).takeIf { it } ?: throw RuntimeException("Failed Attempt")
}
