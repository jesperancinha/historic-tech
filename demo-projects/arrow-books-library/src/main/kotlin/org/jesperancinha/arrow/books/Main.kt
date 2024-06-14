package org.jesperancinha.arrow.books

fun printSeparator(title: String) =
    "*****************************************".run { println("$this $title $this") }

fun main() {
    TypedErrors.main()
    Coroutines.main()
    Resillience.main()
    ImmutableData.main()
    CollectionsAndFunctions.main()
    Design.main()
}
