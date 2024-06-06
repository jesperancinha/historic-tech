package org.jesperancinha.asnsei.guessing

import kotlinx.coroutines.runBlocking

class HighTechGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlocking{
        TODO("Not yet implemented")
    }

}