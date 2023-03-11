package org.jesperancinha.spot.on.domain

import org.springframework.data.annotation.Id
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

data class SpotOn(
    @Id
    val id: UUID?,
    val url:String,
    val name:String,
    val intro:String,
)

interface SpotOnRepository: CoroutineCrudRepository<SpotOn, UUID>