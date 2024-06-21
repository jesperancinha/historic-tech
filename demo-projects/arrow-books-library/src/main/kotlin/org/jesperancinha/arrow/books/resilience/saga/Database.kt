package org.jesperancinha.arrow.books.resilience.saga

import org.jesperancinha.arrow.books.typed.raise.Book

val databaseBooks = mutableMapOf(
    1L to Book(
        id = 1,
        name = "The silence of the kittens",
        isdnNumber = 98765432123456789,
    ),
    2L to Book(
        id = 2,
        name = "Game of Catnip",
        isdnNumber = 123476548901234
    )
)
val reservations = mutableMapOf<Int, ReserveTicket>()
val users = mutableMapOf(
    1 to User(1, "João Esperancinha")
)
