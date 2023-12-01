package org.jesperancinha.cracservice.rest

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
class BlockerController {

    val level by lazy { AtomicInteger(10) }

    @GetMapping
    fun blockingStatus() = level


    @Scheduled(fixedRate = 5000)
    fun scheduleFixedRateTask() {
        level.decrementAndGet()
        println(level)
    }
}