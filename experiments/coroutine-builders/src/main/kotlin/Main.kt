import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import java.time.LocalDateTime
import kotlin.system.measureTimeMillis


data class Donkey(
    val id: Long,
    val name: String
)

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

    runBlocking {
        val donkeySpecies = listOf(
            Donkey(4, "Incinnakey"),
            Donkey(3, "Wallacy"),
            Donkey(2, "Analonkey"),
            Donkey(1, "Reetcoil"),
            Donkey(0, "Cocoloco"),
        )
        val donkeySpeciesWithRecords = listOf(
            DonkeyRecord(4, "Incinnakey"),
            DonkeyRecord(3, "Wallacy"),
            DonkeyRecord(2, "Analonkey"),
            DonkeyRecord(1, "Reetcoil"),
            DonkeyRecord(0, "Cocoloco"),
        )
        val registerDonkeys = fun(donkey: Donkey) = suspend {
            println("Donkey ${donkey.id} with name ${donkey.name} has been registered!")
            delay(100)
        }
        val registerDonkeyRecords = fun(donkey: DonkeyRecord) = suspend {
            println("Donkey ${donkey.id} with name ${donkey.name} has been registered!")
            delay(100)
        }
        measureTimeMillis {
            repeat(10000) {
                registerDonkeys(donkeySpecies.random())
            }
        }.let {
            println("It took $it milliseconds to register all Donkeys")
        }
        measureTimeMillis {
            repeat(10000) {
                registerDonkeyRecords(donkeySpeciesWithRecords.random())
            }
        }.let { println("It took $it milliseconds to register all DonkeyRecords") }

    }

    CoroutineScope(Dispatchers.Unconfined).launch {
        delay(1000)
    }
    GlobalScope.launch {
        delay(10000)

        printCurrentContextInfo("Global")

        val futureScopeReturn = future {
            printCurrentContextInfo("Future")
            1
        }
        val futureValue = futureScopeReturn.get()
        println("Just like in the old days $futureValue")

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
        val withContextReturn = withContext(Dispatchers.IO) {
            printCurrentContextInfo("WithContext")
            1
        }

        launch(newSingleThreadContext("Custom thread")) {
            printCurrentContextInfo("Single Thread Context")
        }

        val value = asyncReturn.await()
        println("The asynchronous value is $value")

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
    println(CoroutineScope(this.coroutineContext))
    println(this.coroutineContext.job)
    println(this.coroutineContext.job.key)
    println(Thread.currentThread())
    println(Thread.currentThread().name)
    println((1..10).joinToString("") { "-" })
}

private suspend fun launchTest() {
    coroutineScope {
        printCurrentContextInfo("launchTest1")
        launch {
            printCurrentContextInfo("launchTest10")
        }
        val launchReturn = launch(start = CoroutineStart.LAZY) {
            delay(100)
            println("LAZY! This will never print if we start lazy, except if we join it")
            printCurrentContextInfo("launchTest11")

        }
        launch(start = CoroutineStart.DEFAULT) {
            delay(50)
            println("NOT LAZY! This will always print if we don't start lazy.")
            printCurrentContextInfo("launchTest12")
        }
        println("If we join it, it will start")
        launchReturn.join()
    }
}