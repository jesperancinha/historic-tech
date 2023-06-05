package org.jesperancinha.keywords.spinoffs


fun main() {
    val nonLocalReturn = recycleCan {
        println("Use the shredder")
        println("Give a sign that you are done!")
        recycleCanCompletion {
            (1..10).forEach {
                if (it == 2) return@forEach
                println("Shipped can $it")
            }
            return@recycleCanCompletion
        }
        return@recycleCan
    }
    val noNonLocalReturn = recycleCanAndForget {
        println("Use the shredder and use materials for other things")
        println("But you don't have to return anything")
        println("But you don't have to return anything")
        // This is not possible because crossinline prevents non-local returns
        // Why is this called crossinline?
        // And why do we need to avoid them?
        // Inline is mandatory because crossinline changes the bytecode of the function.
        recycleCanCompletion {
            (1..10).forEach {
                if (it == 2) return@forEach
                println("Shipped can $it")
            }
            return@recycleCanCompletion
        }
//        return@recycleCan
    }

    println(nonLocalReturn)
    println(noNonLocalReturn)
}

inline fun recycleCan(recycleFunction: () -> Unit) = recycleFunction()
fun recycleCanCompletion(recycleFunction: () -> Unit) = recycleFunction()

inline fun recycleCanAndForget(crossinline recycleFunction: () -> Unit) = recycleFunction()