package org.jesperancinha.overlays

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OService {

    @Transactional
    suspend fun whoAmI() = "I am $this"
    suspend fun call(function: suspend OService.() -> String) = function()
}

inline fun OService.callWorks(block: OService.() -> String): String = block()
