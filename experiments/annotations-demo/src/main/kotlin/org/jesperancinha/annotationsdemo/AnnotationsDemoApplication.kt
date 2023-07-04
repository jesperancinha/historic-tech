package org.jesperancinha.annotationsdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AnnotationsDemoApplication

fun main(args: Array<String>) {
	runApplication<AnnotationsDemoApplication>(*args).start()
}
