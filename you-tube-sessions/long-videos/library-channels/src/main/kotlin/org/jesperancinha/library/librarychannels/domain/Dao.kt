package org.jesperancinha.library.librarychannels.domain

import java.time.LocalDate
import java.util.UUID

data class Book(
    val id: UUID,
    val name: String,
    val pages: Int
)

data class ReaderUser(
    val id: UUID,
    val name:String,
    val address: String,
    val telephone: String,
)
data class Reservation (
    val id: UUID,
    val user: ReaderUser,
    val resevationDate: LocalDate = LocalDate.now(),
    val returnDate: LocalDate,
    val book: Book
)