package org.jesperancinha.annotationsdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnnotationsDemoApplication

fun main(args: Array<String>) {
	runApplication<AnnotationsDemoApplication>(*args)
}
