import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
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
    CoroutineScope(Dispatchers.Unconfined).launch {

    }
    GlobalScope.launch {
        printCurrentContextInfo("Global")

        val futureScopeReturn = future {
            printCurrentContextInfo("Future")
            1
        }
        futureScopeReturn.get()

        val coroutineScopeReturn = coroutineScope {
            printCurrentContextInfo("CoroutineScope")
            1
        }

        val runBlockingReturn = runBlocking {
            printCurrentContextInfo("RunBlocking")
            1
        }
        launchTest()

        val asyncReturn = async {
            printCurrentContextInfo("Async")
            1
        }

        printCurrentContextInfo("BeforeWithContext")
        val withContextReturn = withContext(Dispatchers.IO){
            printCurrentContextInfo("WithContext")
            1
        }

        launch(newSingleThreadContext("Custom thread")) {
            printCurrentContextInfo("Single Thread Context")
        }

        asyncReturn.await()

        println(LocalDateTime.now())
        Thread.sleep(2000)
    }
    println(LocalDateTime.now())
    Thread.sleep(2000)
    println(LocalDateTime.now())
}

private fun CoroutineScope.printCurrentContextInfo(name: String) {
    println((1..10).joinToString("") { "-" })
    println(name)
    println(this.coroutineContext)
    println(this)
    println(this.coroutineContext.job)
    println(this.coroutineContext.job.key)
    println(Thread.currentThread())
    println(Thread.currentThread().name)
    println((1..10).joinToString("") { "-" })
}

private suspend fun launchTest() {
    coroutineScope {
        val launchReturn = launch(start = CoroutineStart.LAZY) {
            delay(100)
            println("LAZY! This will never print if we start lazy, except if we join it")

        }
        launch(start = CoroutineStart.DEFAULT) {
            delay(50)
            println("NOT LAZY! This will always print if we don't start lazy.")
        }
        println("If we join it, it will start")
        launchReturn.join()
    }
}