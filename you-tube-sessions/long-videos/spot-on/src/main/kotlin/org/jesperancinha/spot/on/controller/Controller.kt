package org.jesperancinha.spot.on.controller

import com.fasterxml.jackson.databind.JsonNode
import org.jesperancinha.spot.on.service.SpotOnDto
import org.jesperancinha.spot.on.service.SpotOnService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

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