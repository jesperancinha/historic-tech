import arrow.core.Either
import arrow.core.None
import arrow.core.left
import arrow.core.none
import arrow.core.raise.either
import arrow.core.raise.option
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OptionTest {

    @Test
    fun `should test option`() {
        val optionTest = option {
            none<String>().bind()
        }
        optionTest shouldBe None
    }

    @Test
    fun `should test left call of Either`() {
        val optionTest = either {
            Either.Left("Test").bind()
        }
        optionTest shouldBe Either.Left("Test")
    }
}