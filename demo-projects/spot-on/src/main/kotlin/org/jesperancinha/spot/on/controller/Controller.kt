package org.jesperancinha.spot.on.controller

import com.fasterxml.jackson.databind.JsonNode
import org.jesperancinha.spot.on.service.SpotOnDto
import org.jesperancinha.spot.on.service.SpotOnService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/articles")
class SpotOnController(
    val spotOnService: SpotOnService,
) {

    @PostMapping
    suspend fun postSpotOn(@RequestBody spotOnDto: SpotOnDto) = spotOnService.upsert(spotOnDto)

    @GetMapping
    fun getAllSpotOns() = spotOnService.getAll()

    @PutMapping
    suspend fun putSpotOn(@RequestBody spotOnDto: SpotOnDto) = spotOnService.upsert(spotOnDto)

    @PatchMapping("{id}")
    suspend fun patchSpotOn(@RequestBody jsonObject: JsonNode,@PathVariable id: UUID) = spotOnService
        .patchUpdateUrl(id, jsonObject)

    @RequestMapping(method = [RequestMethod.HEAD], value = ["{id}"])
    suspend fun headSpotOn(@PathVariable id: UUID) = spotOnService.getSpotOnById(id)

}