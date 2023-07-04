package org.jesperancinha.annotationsdemo

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PathVariable
import java.math.BigDecimal

@SpringBootApplication
open class AnnotationsDemoApplication

fun main(args: Array<String>) {
	runApplication<AnnotationsDemoApplication>(*args).start()
}

data class PureInfo(
	val title: String,
	val author: String,
	val vat: BigDecimal,
	val ratePerHour: BigDecimal,
	val ratePerWeek: BigDecimal,
	val ratePerMonth: BigDecimal,
	val isGood: Boolean
)