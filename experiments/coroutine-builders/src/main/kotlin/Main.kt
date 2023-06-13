import kotlinx.coroutines.*
import java.time.LocalDateTime


/**
 * Coroutine builders:
 *
 * launch
 * runBlocking
 * async
 *
 * Coroutine functions
 * coroutineScope
 */
@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) {
    GlobalScope.launch {

        coroutineScope { }

        runBlocking { }

        val launch = launch(start = CoroutineStart.LAZY) {
            delay(100)
            println("LAZY! This will never print if we start lazy, except if we join it")

        }
        launch(start = CoroutineStart.DEFAULT) {
            delay(50)
            println("NOT LAZY! This will always print if we don't start lazy.")
        }
        println("If we join it, it will start")
        // This suspends the current coroutine.
        launch.join()
        println(LocalDateTime.now())
        Thread.sleep(2000)
    }
    println(LocalDateTime.now())
    Thread.sleep(2000)
    println(LocalDateTime.now())
}