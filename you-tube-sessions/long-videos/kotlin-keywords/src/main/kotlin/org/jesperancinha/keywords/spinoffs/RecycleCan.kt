package org.jesperancinha.keywords.spinoffs


fun main() {
    recycleCan {
        println("Use the shredder")
        return@recycleCan
    }
    recycleCanAndForget {
        println("Use the shredder and use materials for other things")
        // This is not possible because crossinline prevents non-local returns
        // Why is this called crossinline?
        // And why do we need to avoid them?
        // Inline is mandatory because crossinline changes the bytecode of the function.
        // return@recycleCan
    }
}

fun recycleCan( recycleFunction: ()-> Unit) {
    recycleFunction()
}
inline fun recycleCanAndForget(crossinline recycleFunction: ()-> Unit) {
    recycleFunction()
}
