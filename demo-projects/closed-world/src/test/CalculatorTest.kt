import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun `should make add and join calls`() {
        Calculator.main(arrayOf("Add", "add", "1", "2"))
        Calculator.main(arrayOf("Add", "join", "1", "2"))
    }
}