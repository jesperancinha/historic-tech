package org.jesperancinha.youtube.spoton.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.LocalDateTime
import java.util.*

data class SpotOn(
    val id : UUID,
    val name : String,
    val quantity: Long,
    val localDateTime: LocalDateTime
)

interface SpotOnInterface: CoroutineCrudRepository<SpotOn, UUID>