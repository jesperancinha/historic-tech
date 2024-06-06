import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.asnsei.guessing.CommonGuessingGameService
import org.jesperancinha.asnsei.guessing.CoroutinesGameRetry
import org.jesperancinha.asnsei.guessing.GameFailedException
import org.jesperancinha.asnsei.guessing.TraditionalGameRetry
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CoroutinesGameRetryTest {

    val game by lazy { CoroutinesGameRetry(object: CommonGuessingGameService() {
        override fun assertResult(inputNumber: Long): Boolean {
            return  inputNumber == 100L
        }
    }) }

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