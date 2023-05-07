package org.jesperancinha.overlays

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OService {

    @Transactional
    suspend fun whoAmI() = "$this"
    suspend fun call(function: suspend OService.() -> String): String {
        println("Inside the normal function literal with receiver I am $this")
        return function()
    }
}

inline fun OService.callWorks(block: OService.() -> String): String {
    println("Inside the inlined function literal with receiver I am $this")
    return block()
}
