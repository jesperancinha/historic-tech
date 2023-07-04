package org.jesperancinha.annotationsdemo

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

val RATE_PER_PROJECT = BigDecimal(100_000)

@SpringBootApplication
open class AnnotationsDemoApplication

fun main(args: Array<String>) {
    runApplication<AnnotationsDemoApplication>(*args).start()
}

data class PerfectInfo(
    @param:JsonProperty("title")
    val title: String,
    @param:JsonProperty("author")
    val author: String,
    @param:JsonProperty("vat")
    val vat: BigDecimal,
    @param:JsonProperty("ratePerHour")
    val ratePerHour: BigDecimal,
    @param:JsonProperty("ratePerWeek")
    val ratePerWeek: BigDecimal,
    @param:JsonProperty("ratePerMonth")
    val ratePerMonth: BigDecimal,
    @param:JsonProperty("good")
    val isGood: Boolean
) {
    @JsonProperty("ratePerProject")
    lateinit var ratePerProject:BigDecimal

    fun modernizeWork() {
        ratePerProject = RATE_PER_PROJECT
    }
}

@RestController
open class PureInfoController {

    @PostMapping
    open fun getConstantPerfectInfo(@RequestBody perfectInfo: PerfectInfo) = run {
        println("Received: $perfectInfo")
        ResponseEntity.ok().body(perfectInfo)
    }
}