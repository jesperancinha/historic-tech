package org.jesperancinha.arrow.books

import org.jesperancinha.arrow.books.collections.MemoizationService
import org.jesperancinha.arrow.books.coroutines.races.RacesService
import org.jesperancinha.arrow.books.design.either.DefendersOfTheGalaxyService
import org.jesperancinha.arrow.books.immutable.optics.GuardiansOfTheGalaxyAddressesService
import org.jesperancinha.arrow.books.resilience.circuit.UrsusService
import org.jesperancinha.arrow.books.resilience.saga.LibraryService
import org.jesperancinha.arrow.books.typed.raise.BooksWithErrorsService

fun printSeparator(title: String) =
    "-------".run { println("$this $title") }

fun main() {
    RacesService.main()
    MemoizationService.main()
    LibraryService.main()
    UrsusService.main()
    DefendersOfTheGalaxyService.main()
    GuardiansOfTheGalaxyAddressesService.main()
    BooksWithErrorsService.main()
}
