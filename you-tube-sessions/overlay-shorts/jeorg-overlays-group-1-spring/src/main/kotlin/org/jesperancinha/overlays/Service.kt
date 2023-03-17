package org.jesperancinha.overlays

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Service {

    @Transactional
   suspend fun whoAmI() = "I am $this"
    suspend fun call(function: suspend org.jesperancinha.overlays.Service.() -> String) = function()
}