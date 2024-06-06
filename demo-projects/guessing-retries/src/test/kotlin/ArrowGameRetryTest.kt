import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.asnsei.guessing.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArrowGameRetryTest {
    val game by lazy {
        ArrowGameRetry(object : CommonGuessingGameService() {
            override fun assertResult(inputNumber: Long): Boolean {
                return inputNumber == 100L
            }

        })
    }

    @Test
    fun `should resolve correctly 3 times when number fails resulting in fail`() {
        val checkNumber = game.checkNumber(200)
        checkNumber.isFailure.shouldBeTrue()
        val gameResult = (checkNumber.exceptionOrNull() as GameFailedException).gameResult
        gameResult.retries shouldBe 3
    }

    @Test
    fun `should resolve correctly at first try`() {
        val checkNumber = game.checkNumber(100)
        checkNumber.isSuccess.shouldBeTrue()
        val gameResult = checkNumber.getOrNull().shouldNotBeNull()
        gameResult.retries shouldBe 1
    }
}