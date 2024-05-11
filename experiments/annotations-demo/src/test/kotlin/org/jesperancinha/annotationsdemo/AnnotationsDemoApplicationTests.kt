package org.jesperancinha.annotationsdemo

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
class AnnotationsDemoApplicationTests @Autowired constructor(
    private val mockMvc: MockMvc
) {

    val objectMapper by lazy { ObjectMapper() }

    @Test
    fun contextLoads() {
        val perfectInfo = PerfectInfo(
            title = "Perfect Title",
            author = "Perfect Author",
            vat = BigDecimal(1.34),
            ratePerHour = BigDecimal(1.22),
            ratePerWeek = BigDecimal(100),
            ratePerMonth = BigDecimal(8000),
            isGood = true,
            amount = 10
        )
        perfectInfo.modernizeWork()
        mockMvc.perform(
            post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(perfectInfo))
        ).andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString
            .should {
                val value = objectMapper.readValue(it.replace("wow","amount"), PerfectInfo::class.java)
                value.isGood.shouldBeTrue()
                value.ratePerProject shouldBe RATE_PER_PROJECT
            }

    }

}
