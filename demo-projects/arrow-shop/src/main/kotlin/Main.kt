package org.jesperancinha

import arrow.core.raise.nullable

data class Arrow(
    val length: Int,
    val feathering: Int
)

lateinit var arrows: List<Arrow?>

fun getArrowsSorted(fail: Boolean = false, fail2: Boolean = false) = nullable {
    val all = arrows.bind()
    if (fail)
        ensure(all.count { it.bind().length > 10 } == 0)
    println(all)
    val filtered = all.filter { it.bind().length == 10 }.bindAll()
    if (fail2)
        log(filtered.takeIf { it.size > 1000 }.bind())
    log(filtered)
    filtered
}.orEmpty()

fun log(filtered: List<Arrow>) {
    println(filtered)
}

fun main() {
    arrows = listOf(
        Arrow(20, 5),
        Arrow(10, 5),
        Arrow(15, 1)
    )
    val test = getArrowsSorted()
    println(test)
    println("***************************")
    val test2 = getArrowsSorted(true)
    println(test2)
    println("***************************")
    val test3 = getArrowsSorted(fail2 = true)
    println(test3)
}