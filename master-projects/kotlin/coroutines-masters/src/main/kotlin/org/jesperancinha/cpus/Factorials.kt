package org.jesperancinha.cpus
import kotlinx.coroutines.*
import java.math.BigInteger

suspend fun calculateFactorial(number: Int): BigInteger = coroutineScope {
    val result = withContext(Dispatchers.Default) {
        var result = BigInteger.ONE
        for (i in 1..number) {
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        delay(1000)
        println("Calculation finished")
        result
    }
    return@coroutineScope result
}

fun main() {
    runBlocking {
        val numberToCalculate = 10000
        val result = async { calculateFactorial(numberToCalculate) }
        println("Waiting for calculation")
        println("Factorial of $numberToCalculate is: ${result.await()}")
    }
}
