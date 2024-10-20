import arrow.core.None
import arrow.core.none
import arrow.core.raise.option
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OptionTest {

    @Test
    fun `should test option`(){
        val optionTest = option {
            none<String>().bind()
        }
        optionTest shouldBe None
    }
}