import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

/**
 * WARNING!
 * This project exemplified terrible practices in code
 * This project serves to illustrate a starting legacy point, and it serves educational purposes only
 *
 * This is the case:
 *
 * 1 Billion bottles are stored in the warehouse.
 * Each bottle is filled with approximately 1 Lt of Juice
 * The computer has stored and measured all individual quantities precisely
 * We want to know how much juice did we actually spent and what is the standard deviation of our results
 */
fun main(args: Array<String>) {

    val database = JuiceDatabase(UUID.randomUUID())

    measureTimeMillis {
        var realQuantity= 0.0
        database.content.forEach {
            realQuantity += it.quantity
        }
        println("""
    The expected quantity was 
    ${(database.content.size * 1000.0)}
    The actual quantity was 
    $realQuantity
    """.trimIndent())
        val mean = realQuantity/FIFTY_MILLION
        var powSum = 0.0
        database.content.forEach {
            powSum += (it.quantity - mean).toDouble().pow(2.0)
        }
        val std =  sqrt(powSum / FIFTY_MILLION)
        println("""
    The standard deviation is $std%
    """.trimIndent())
    }.let {
        println("Took $it milliseconds to perform all operations")
    }


}

