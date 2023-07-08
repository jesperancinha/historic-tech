import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun `should test the main function`() {
        main()
    }

    @Test
    fun `should test the coroutine builders example`() {
        baloneyAfterReleasingDonkeys()
    }

    @Test
    fun `should test the baloney sandwich after releasing donkeys example`() {
        coroutineBuildersExample()
    }

    @Test
    fun `should perform coroutine LAZY property test`() = runBlocking{
        coroutineLazyTest()
    }
}