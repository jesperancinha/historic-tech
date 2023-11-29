package org.jesperancinha.coroutines.rest

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val IO_CALL_DURATION_MS: Long = 100

@RestController
@RequestMapping("test")
class SimpleRestController {

    @GetMapping
    suspend fun getTest() {
        delay(IO_CALL_DURATION_MS)
    }
}
