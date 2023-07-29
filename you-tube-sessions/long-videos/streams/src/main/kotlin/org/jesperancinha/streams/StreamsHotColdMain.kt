package org.jesperancinha.streams

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.lang.Math.random

fun main() {
    runChannelExample()
    Thread.sleep(1000)
    runChannelCloseExample()
    Thread.sleep(1000)
}

private fun runChannelCloseExample() {
    CoroutineScope(Dispatchers.IO).launch {
        val channel = Channel<Int>()
        launch {
            repeat(10) {
                channel.send((random() * 123).toInt())
            }
            channel.close()
        }
        for (y in channel) println(y)
        println("Complete!")
    }
}

private fun runChannelExample() {
    CoroutineScope(Dispatchers.IO).launch {
        val channel = Channel<Int>()
        launch {
            repeat(10) {
                channel.send((random() * 123).toInt())
            }
        }
        repeat(10) { println(channel.receive()) }
        println("Complete!")
    }
}
