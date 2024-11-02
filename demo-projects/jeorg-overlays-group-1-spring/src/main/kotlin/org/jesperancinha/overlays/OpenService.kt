package org.jesperancinha.overlays

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OpenService {

    @Transactional
    suspend fun whoAmI() = "$this"
    suspend fun call(function: suspend OpenService.() -> String): String {
        println("Inside the normal function literal with receiver I am $this")
        return function()
    }
}

inline fun OpenService.callWorks(block: OpenService.() -> String): String {
    println("Inside the inlined function literal with receiver I am $this")
    return block()
}
