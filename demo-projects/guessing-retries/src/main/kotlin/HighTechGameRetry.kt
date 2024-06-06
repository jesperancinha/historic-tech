package org.jesperancinha.asnsei.guessing

class HighTechGameRetry(guessingService: CommonGuessingGameService) : Retry<Result<GameOutput>>(guessingService) {
    override fun checkNumber(inputNumber: Long): Result<GameOutput> = runBlo{
        TODO("Not yet implemented")
    }

}