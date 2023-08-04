package org.jesperancinha.library.librarychannels.controller

import org.jesperancinha.library.librarychannels.domain.Reservation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class BooksController {

    @PostMapping
    suspend fun sendReservation(@RequestBody reservation: Reservation) = ResponseEntity.ok()
}