package org.jesperancinha.spot.on.controller

import org.jesperancinha.spot.on.service.SpotOnDto
import org.jesperancinha.spot.on.service.SpotOnService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SpotOnController(
    val spotOnService: SpotOnService
) {

    @PostMapping

    suspend fun postSpotOn(@RequestBody spotOnDto: SpotOnDto) = spotOnService.upsert(spotOnDto)

    @GetMapping
    fun getAllSpotOns() = spotOnService.getAll()

    @PutMapping
    suspend fun putSpotOn(@RequestBody spotOnDto: SpotOnDto) = spotOnService.upsert(spotOnDto)
}