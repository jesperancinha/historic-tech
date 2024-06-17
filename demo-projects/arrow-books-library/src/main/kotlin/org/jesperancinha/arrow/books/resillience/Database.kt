package org.jesperancinha.arrow.books.resillience

import org.jesperancinha.arrow.books.Book

val databaseBooks = mutableMapOf(
    1 to Book(
        id = 1,
        name = "The silence of the kittens",
        isdnNumber = 98765432123456789,
    ),
    2 to Book(
        id = 2,
        name = "Game of Catnip",
        isdnNumber = 123476548901234
    )
)
val reservations = mutableMapOf<Int, ReserveTicket>()
val users = mutableMapOf(
    1 to User(1, "João Esperancinha")
)
