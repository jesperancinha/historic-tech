import MainJava.N_DONKEYS
import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import java.lang.Thread.sleep
import java.time.LocalDateTime.now
import java.util.concurrent.Executors
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
fun main() {
    baloneyAfterReleasingDonkeys()
    coroutineBuildersExample()
}

/**
 * This test illustrates in a nutshell the different coroutine builders
 */
@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
fun coroutineBuildersExample() {
    val globalLaunch = GlobalScope.launch {
        printCurrentContextInfo("Global")

        val futureScopeReturn = future {
            printCurrentContextInfo("Future")
            12
        }
        val futureValue = futureScopeReturn.get()
        println("Just like in the old days $futureValue")

        val coroutineScopeReturn = coroutineScope {
            printCurrentContextInfo("CoroutineScope")
            123
        }
        println("This what we get from a coroutineScope: $coroutineScopeReturn")

        val runBlockingReturn = runBlocking {
            printCurrentContextInfo("RunBlocking")
            1234
        }
        println("This is the result of a blocking coroutine scope $runBlockingReturn")

        launchTest()

        val asyncReturn = async {
            printCurrentContextInfo("Async")
            12345
        }
        println("This is what we immediately get returned after async $asyncReturn")

        printCurrentContextInfo("BeforeWithContext")
        val withContextReturn = withContext(Dispatchers.IO) {
            printCurrentContextInfo("WithContext")
            123456
        }
        println("This is what we immediately after we return withContextReturn $withContextReturn")

        val launchSingleThreadContext = launch(newSingleThreadContext("Custom thread")) {
            printCurrentContextInfo("Single Thread Context")
        }

        val value = asyncReturn.await()
        println("The asynchronous value is $value")

        println(now())
        sleep(2000)
    }
    println("This is what we get after a global launch: $globalLaunch")
    println(now())
    sleep(2000)
    println(now())
}


/**
 * In this test we are comparing the performance between using
 * Java Records
 * and
 * Kotlin Data Classes
 * when trying to fulfill the good practice of assuring immutability in our code
 */
fun baloneyAfterReleasingDonkeys() {
    CoroutineScope(Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()).launch {
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

        measureTimeMillis {
            (1..N_DONKEYS).map {
                async {
                    donkeySpeciesWithRecords.random()
                        .let { DonkeyRecord(it.id, it.name) }
                    delay(1)
                }
            }.awaitAll()
        }.let { println("It took $it milliseconds to give a baloney sandwich to everybody after watching the release of $N_DONKEYS Record Donkeys") }

        measureTimeMillis {
            (1..N_DONKEYS).map {
                async {
                    donkeySpecies.random()
                        .let { Donkey(it.id, it.name) }
                    delay(1)
                }
            }.awaitAll()
        }.let {
            println("It took $it milliseconds to a give baloney sandwich to everybody after watching the release of $N_DONKEYS Data Class Donkeys")
        }

    }
    sleep(10000)

}

private fun donkeyTastesABalloneySandwichOnSuspendFunctions() {
    CoroutineScope(Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()).launch {
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
        val giveBaloneyToDataClassDonkey = fun(donkey: Donkey) = suspend {
            println("Donkey ${donkey.id} with name ${donkey.name} has been released!")
            delay(1)
        }
        val giveBaloneyToRecordDonkey = fun(donkey: DonkeyRecord) = suspend {
            println("Donkey ${donkey.id} with name ${donkey.name} has been released!")
            delay(1)
        }
        measureTimeMillis {
            (1..N_DONKEYS).map {
                async {
                    giveBaloneyToDataClassDonkey(donkeySpecies.random()
                        .let { Donkey(it.id, it.name) })()
                }
            }.awaitAll()
        }.let {
            println("It took $it milliseconds to a give baloney sandwich to everybody after watching all data class Donkeys")
        }
        measureTimeMillis {
            (1..N_DONKEYS).map {
                async {
                    giveBaloneyToRecordDonkey(
                        donkeySpeciesWithRecords.random()
                            .let { DonkeyRecord(it.id, it.name) })()
                }
            }.awaitAll()
        }.let { println("It took $it milliseconds to give a baloney sandwich to everybody after watching all record Donkeys") }


    }
    sleep(10000)

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