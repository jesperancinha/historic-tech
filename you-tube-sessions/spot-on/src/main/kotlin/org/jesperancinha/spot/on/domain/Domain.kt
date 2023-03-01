package org.jesperancinha.spot.on.domain

import org.springframework.data.repository.CrudRepository
import java.util.UUID

data class SpotOn(
    val id: UUID,
    val url:String,
    val name:String,
    val intro:String,
)

interface SpotOnRepository : CrudRepository<SpotOn, UUID>