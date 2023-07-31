package org.jesperancinha.streams

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.lang.Math.random
import java.time.LocalDateTime
import kotlin.math.sqrt

data class Frog(
    val name: String = "Frog" + (random() * 1000).toInt()
) {
    override fun toString() = name
}

fun main() {
    runChannelExample()
    pause()
    runChannelCloseExample()
    pause()
    runProducerChannelExample()
    pause()
    runPipelineChannelsExample()
    pause()
    runFanOutExample()
    pause()
    runFanInExample()
    pause()
    runBufferedChannelExample()
    pause()
}

private fun runBufferedChannelExample() {
    runBlocking {
        val channel = Channel<Frog>(4)
        val sender = launch {
            repeat(10) {
                val frog = Frog()
                println("Sending frog $frog on position $it through the channel!")
                channel.send(frog)
            }
        }
        println("There are now 4 forgs buffered and the channel is suspended")
        delay(1000)
        println("We are consuming 1 and therefore the buffer is free to receive another item")
        channel.receive()
        delay(1000)
        sender.cancel()
    }
}


private fun runFanInExample() {
    CoroutineScope(Dispatchers.IO).launch {
        println("FAN IN TEST")
        val cutlery = arrayOf("Fork", "Knife", "Spoon", "Stick", "Plate", "Pan", "Saucepan")
        val cooks = arrayOf("Jenny", "Jamie", "Oliver", "Olivier", "Olivia", "Olga", "Helga")
        val producer = Channel<String>()
        for (item in cutlery) {
            launch {
                while (true) {
                    delay(100L)
                    producer.send(item)
                }
            }
        }
        repeat(6) {
            println("Cook ${cooks[it]} took a(n) ${producer.receive()}")
        }
        delay(250L)
        coroutineContext.cancelChildren()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun runFanOutExample() {
    println("FAN OUT TEST")
    CoroutineScope(Dispatchers.IO).launch {
        val cutlery = arrayOf("Fork", "Knife", "Spoon", "Stick", "Plate", "Pan", "Saucepan")
        val cooks = arrayOf("Jenny", "Jamie", "Oliver", "Olivier", "Olivia", "Olga", "Helga")
        val producer = produce {
            while (true) {
                send(cutlery.random())
                delay(100)
            }
        }
        repeat(cooks.size) {
            launch {
                for (item in producer) {
                    println("Cook ${cooks[it]} took a(n) $item")
                }
            }
        }
        delay(250)
        producer.cancel()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun runPipelineChannelsExample() {
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
        val randomNumbers: ReceiveChannel<Int> = produce {
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
    Thread.sleep(500)
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
