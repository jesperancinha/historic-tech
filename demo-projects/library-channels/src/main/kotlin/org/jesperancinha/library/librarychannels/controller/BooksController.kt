package org.jesperancinha.library.librarychannels.controller

import org.jesperancinha.library.librarychannels.domain.Reservation
import org.jesperancinha.library.librarychannels.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class BooksController(
    val bookService: BookService
) {

    @PostMapping
    suspend fun sendReservation(@RequestBody reservation: Reservation) = run {
        bookService.signalReservaton(reservation)
        ResponseEntity.ok().body("Message received")
    }
}