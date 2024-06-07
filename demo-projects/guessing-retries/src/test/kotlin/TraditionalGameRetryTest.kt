import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.asnsei.guessing.CommonGuessingGameService
import org.jesperancinha.asnsei.guessing.GameFailedException
import org.jesperancinha.asnsei.guessing.HighTechGameRetry
import org.jesperancinha.asnsei.guessing.TraditionalGameRetry
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.atomic.AtomicInteger

class TraditionalGameRetryTest {

    val game by lazy { TraditionalGameRetry(object: CommonGuessingGameService() {
        override fun assertResult(inputNumber: Long): Boolean {
            return  inputNumber == 100L
        }
    }) }

    @Test
    fun `should fail correctly 3 times when number fails resulting in fail`() {
        val checkNumber = game.checkNumber(200)
        checkNumber.isFailure.shouldBeTrue()
        val gameResult = (checkNumber.exceptionOrNull() as GameFailedException).gameResult
        gameResult.retries shouldBe 3
    }

    @Test
    fun `should resolve correctly on second try`() {
        val gameTest by lazy {
            TraditionalGameRetry(object : CommonGuessingGameService() {
                val counter = AtomicInteger(0)
                override fun assertResult(inputNumber: Long): Boolean {
                    if (counter.incrementAndGet() == 2) {
                        return inputNumber == 100L
                    }
                    return inputNumber == 200L
                }

            })
        }
        val checkNumber = gameTest.checkNumber(100)
        checkNumber.isSuccess.shouldBeTrue()
        val gameResult = checkNumber.getOrNull().shouldNotBeNull()
        gameResult.retries shouldBe 2
    }

    @Test
    fun `should resolve correctly at first try`() {
        val checkNumber = game.checkNumber(100)
        checkNumber.isSuccess.shouldBeTrue()
        val gameResult = checkNumber.getOrNull().shouldNotBeNull()
        gameResult.retries shouldBe 1
    }
}