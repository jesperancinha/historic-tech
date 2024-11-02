package org.jesperancinha.arrow.books.immutable.optics

import arrow.optics.optics
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TheVillageOfThePeopleOpticsAnnotationTest {

    @optics
    data class User(val name: String, val address: Address?) {
        companion object
    }

    @optics
    data class Address(val street: String, val city: String?) {
        companion object
    }

    @Test
    fun `should get an Optional from the helper classes and companion objects`(){
        val userWithCity = User(
            name = "Alice",
            address = Address(
                street = "123 Main St",
                city = "Springfield"
            )
        )
        val userWithoutCity = User(
            name = "Bob",
            address = Address(
                street = "456 Elm St",
                city = null
            )
        )

        val city1 = User.address.city.getAll(userWithCity)
        val city2 = User.address.city.getAll(userWithoutCity)

        city1.first() shouldBe "Springfield"
        city2.shouldBeEmpty()

        val updatedUser = User.address.city.set(userWithCity, "Shelbyville")
        println(updatedUser)
        println(city1)
        println(city2)
    }
}