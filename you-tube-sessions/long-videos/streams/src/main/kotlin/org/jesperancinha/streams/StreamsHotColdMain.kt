package org.jesperancinha.streams

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import java.lang.Math.random
import java.time.LocalDateTime
import kotlin.math.sqrt

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runChannelExample()
    pause()
    runChannelCloseExample()
    pause()
    runProducerChannelExample()
    pause()
    runInfiniteProducingChannel()
    pause()
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun runInfiniteProducingChannel() {
    CoroutineScope(Dispatchers.IO).launch {
        val currentSecondChannel = produce {
            while (true) send(LocalDateTime.now().nano)
        }
        val sqrtCurrentSecondChannel = produce {
            for (number in currentSecondChannel) send(sqrt(number.toDouble()))
        }
        repeat(NUMBERS_FOR_EXAMPLE) {
            println("These are the first square roots for current nanoseconds: ${sqrtCurrentSecondChannel.receive()}")
        }
        println("Complete!")
        coroutineContext.cancelChildren()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun runProducerChannelExample() {
    CoroutineScope(Dispatchers.IO).launch {
        val randomNumbers:ReceiveChannel<Int> = produce {
            for (x in 1..NUMBERS_FOR_EXAMPLE) send((random() * 123).toInt())
        }
        println("Random numbers is a ${randomNumbers.javaClass}")
        println("Random numbers is a ${randomNumbers::class}")
        println("A channel is a receive channel and a produce channel at the same time}")
        randomNumbers.consumeEach { println(it) }
        println("Complete!")
    }
}

private fun pause() {
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
