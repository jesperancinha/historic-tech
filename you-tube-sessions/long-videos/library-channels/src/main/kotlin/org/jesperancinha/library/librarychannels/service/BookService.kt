package org.jesperancinha.library.librarychannels.service;

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.toList
import org.jesperancinha.library.librarychannels.domain.Reservation
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BookService {
    val statsChannel by lazy {
        Channel<Reservation>()
    }

    val resultChannel by lazy {
        Channel<Int>()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Scheduled(cron = "0 * * * * *")
    fun scheduleStats() {
        runBlocking {
            withTimeout(5000) {
                repeat(10) {
                    launch {
                        resultChannel.send(statsChannel.receive().book.pages)
                    }
                }
                    val totalPageCount = resultChannel.tryReceive()
                        .onFailure {
                         println("Nothing received!")
                        }
                    println("We just received all reservations sent to the channel at one point in time. This is known as a Fan-Out")
                    println("We did send data from multiple coroutines, which makes this also a Fan-In")
                    println("This is in fact a mixed operation: ")
                    println("Several coroutines send data to one channel (Fan-In))")
                    println("Several coroutines receive data from one channel (Fan-Out")
                    println("Once the data is receive, another channel receives all the data from the multiple coroutines that sent the individual number of pages (Fan-In)")
                    println("This last operation is known as a Fan-In also")
                    println("The result is ${totalPageCount.getOrNull() ?: 0} pages!")

            }
        }
    }
}
