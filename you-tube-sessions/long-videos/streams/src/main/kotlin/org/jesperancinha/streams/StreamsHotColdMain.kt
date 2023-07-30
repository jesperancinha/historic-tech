package org.jesperancinha.streams

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import java.lang.Math.random

fun main() {
    runChannelExample()
    pause()
    runChannelCloseExample()
    Thread.sleep(1000)
}

private const val NUMBERS_FOR_EXAMPLE = 10

private fun runChannelCloseExample() {
    CoroutineScope(Dispatchers.IO).launch {
        val channel = Channel<Int>()
        launch {
            repeat(NUMBERS_FOR_EXAMPLE) {
                channel.send((random() * 123).toInt())
            }
            channel.close()
            runCatching {
                repeat(NUMBERS_FOR_EXAMPLE) {
                    channel.send((random() * 123).toInt())
                }
            }.onFailure {
                println("Failed to send an extra of $NUMBERS_FOR_EXAMPLE numbers because of ${it.javaClass}")
            }
        }
        for (y in channel) println(y)
        println("Complete!")
    }
}

private fun runChannelExample() {
    CoroutineScope(Dispatchers.IO).launch {
        val channel = Channel<Int>()
        launch {
            repeat(NUMBERS_FOR_EXAMPLE) {
                channel.send((random() * 123).toInt())
            }
        }
        repeat(NUMBERS_FOR_EXAMPLE) { println(channel.receive()) }
        println("Complete!")
    }
}
