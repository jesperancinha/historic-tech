package org.jesperancinha.library.librarychannels

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class LibraryChannelsApplication

fun main(args: Array<String>) {
	runApplication<LibraryChannelsApplication>(*args)
}
