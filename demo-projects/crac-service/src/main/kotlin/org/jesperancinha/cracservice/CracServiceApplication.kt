package org.jesperancinha.cracservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CracServiceApplication

fun main(args: Array<String>) {
	runApplication<CracServiceApplication>(*args)
}
