package org.jesperancinha.spot.on.service

import com.fasterxml.jackson.databind.JsonNode
import org.jesperancinha.spot.on.domain.SpotOn
import org.jesperancinha.spot.on.domain.SpotOnRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.util.*

class SpotOnDto(
    val id: UUID? = null,
    val url: String,
    val name: String,
    val intro: String,
)

@Service
class SpotOnService(
    val spotOnRepository: SpotOnRepository,
) {
    @Transactional
    suspend fun upsert(spotOnDto: SpotOnDto) = spotOnRepository.save(spotOnDto.toData()).toDto()

    fun getAll() = spotOnRepository.findAll()
    suspend fun patchUpdateUrl(id: UUID, jsonObject: JsonNode): SpotOnDto {
        if(jsonObject.get("op").textValue() == "replace"){
            if (jsonObject.get("path").textValue() != "url") {
                throw RuntimeException("This endpoint only updates URL, please update your request")
            }
            val spotOn = requireNotNull(spotOnRepository.findById(id))
            return spotOnRepository.save(spotOn.applyPatch(jsonObject)).toDto()
        } else {
            throw RuntimeException("This endpoint only performs updates to the URL parameter")
        }
    }
}

private fun SpotOn.applyPatch(jsonObject: JsonNode)  =
    jsonObject.get("value").textValue().let { this.copy(url = it) }

fun SpotOnDto.toData() = SpotOn(
    id = this.id,
    url = this.url,
    name = this.name,
    intro = this.intro
)

fun SpotOn.toDto() = SpotOnDto(
    id = this.id,
    url = this.url,
    name = this.name,
    intro = this.intro
)