package org.jesperancinha.coroutines

import kotlinx.coroutines.*
import org.jesperancinha.coroutines.CoroutinesDemoApplication.Companion.logger
import org.jesperancinha.coroutines.rest.IO_CALL_DURATION_MS
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import java.lang.Integer.max
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

@SpringBootApplication
class CoroutinesDemoApplication {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CoroutinesDemoApplication::class.java)
    }
}

suspend fun main(args: Array<String>): Unit = coroutineScope {
    runApplication<CoroutinesDemoApplication>(*args).start()

    val threadCount = 1000
    val blockingExecutionTime: Long = 100
    val availableProcessors = Runtime.getRuntime().availableProcessors()

    defaultDispatcherTest(threadCount, blockingExecutionTime, availableProcessors)
    defaultIOTest(threadCount, blockingExecutionTime, availableProcessors)
    defaultUnconfinedTest(threadCount, blockingExecutionTime, availableProcessors)

    exitProcess(0)
}

private suspend fun defaultUnconfinedTest(
    threadCount: Int,
    blockingExecutionTime: Long,
    availableProcessors: Int,
) {
    val unconfinedDefaultAvailableThreadCount = 1
    val expectedUnconfinedExecutionTime = threadCount * blockingExecutionTime / unconfinedDefaultAvailableThreadCount
    logger.info("Starting Thread count test on Dispatchers.Unconfined. Current processors available: $availableProcessors")
    withContext(Dispatchers.Unconfined) {
        logger.info("Current Thread is ${Thread.currentThread().name}")
        measureTimeMillis {

            (0 until threadCount).map {
                async {
                    Thread.sleep(blockingExecutionTime)
                    val count = Thread.getAllStackTraces()
                        .keys.filter { it.state == Thread.State.RUNNABLE }.count {
                            it.name.contains("DefaultDispatcher") || it.name.contains("main")
                        }
                    if (it == threadCount / 2)
                        logger.info("Halfway we are running $count Threads")
                    if (count > unconfinedDefaultAvailableThreadCount) {
                        logger.warn("Running $count")
                    }
                    if (count < availableProcessors) {
                        logger.debug("We are still running only $count threads")
                    }
                }
            }.awaitAll()
        }.let {
            logger.info("Expected to last take around $expectedUnconfinedExecutionTime ms and it took $it ms")
            logger.info(("Overhead is ${it - expectedUnconfinedExecutionTime} ms"))
        }
    }
}

private suspend fun defaultIOTest(
    threadCount: Int,
    blockingExecutionTime: Long,
    availableProcessors: Int,
) {
    val ioAvailableThreadCount = max(availableProcessors, 64)
    val expectedIOExecutionTime = threadCount * (blockingExecutionTime + IO_CALL_DURATION_MS) / ioAvailableThreadCount
    logger.info("Starting Thread count test on Dispatchers.Default. Current processors available: $availableProcessors")
    val restTemplate = RestTemplate()
    withContext(Dispatchers.IO) {
        val maxHit = AtomicBoolean()
        logger.info("Current Thread is ${Thread.currentThread().name}")
        measureTimeMillis {
            (0 until threadCount).map {
                async {
                    Thread.sleep(blockingExecutionTime)
                    val count = Thread.getAllStackTraces()
                        .keys.filter { it.state == Thread.State.RUNNABLE }.count {
                            it.name.contains("DefaultDispatcher")
                        }
                    if (it == threadCount / 2)
                        logger.info("Halfway we are running $count Threads")
                    if (restTemplate.getForEntity("http://localhost:8080/test", Unit::class.java)
                            .statusCode != HttpStatus.OK
                    ) {
                        throw RuntimeException()
                    }
                    if (count == ioAvailableThreadCount && !maxHit.get()) {
                        logger.info("We've hit the max $count allowed threads!")
                        maxHit.set(true)
                    }
                    if (count > ioAvailableThreadCount) {
                        logger.warn("Running $count")
                    }
                    if (count < availableProcessors) {
                        logger.debug("We are still running only $count threads")
                    }
                }
            }.awaitAll()
        }.let {
            logger.info("Expected to last take around $expectedIOExecutionTime ms and it took $it ms")
            logger.info(("Overhead is ${it - expectedIOExecutionTime} ms"))
        }
    }
}

private suspend fun defaultDispatcherTest(
    threadCount: Int,
    blockingExecutionTime: Long,
    availableProcessors: Int,
) {
    val expectedDefaultExecutionTime = threadCount * blockingExecutionTime / availableProcessors
    logger.info("Starting Thread count test on Dispatchers.Default. Current processors available: $availableProcessors")
    withContext(Dispatchers.Default) {
        val maxHit = AtomicBoolean()
        logger.info("Current Thread is ${Thread.currentThread().name}")
        measureTimeMillis {
            (0 until threadCount).map {
                async {
                    Thread.sleep(blockingExecutionTime)
                    val count = Thread.getAllStackTraces()
                        .keys.filter { it.state == Thread.State.RUNNABLE }
                        .count { it.name.contains("DefaultDispatcher") }
                    if (it == threadCount / 2)
                        logger.info("Halfway we are running $count Threads")
                    if (count == availableProcessors && !maxHit.get()) {
                        logger.info("We've hit the max $count processors!")
                        maxHit.set(true)
                    }
                    if (count > availableProcessors) {
                        logger.warn("Running $count")
                    }
                }
            }.awaitAll()
        }.let {
            logger.info("Expected to last take around $expectedDefaultExecutionTime ms and it took $it ms")
            logger.info(("Overhead is ${it - expectedDefaultExecutionTime} ms"))
        }
    }
}
