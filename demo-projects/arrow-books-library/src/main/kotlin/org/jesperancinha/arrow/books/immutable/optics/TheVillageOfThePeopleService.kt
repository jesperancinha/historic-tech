package org.jesperancinha.arrow.books.immutable.optics

import arrow.optics.optics

@JvmInline
@optics
value class Age(val age: Int) {
    companion object
}

@optics
data class Person(val name: String, val age: Age, val address: Address) : User {
    companion object
}

@optics
data class Address(val street: Street, val city: City) {
    companion object
}

@optics
data class Street(val name: String, val number: Int?) {
    companion object
}

@optics
data class City(val name: String, val country: String) {
    companion object
}

@optics
sealed interface User {
    companion object
}

@optics
data class Company(val name: String, val country: String) : User {
    companion object
}

class TheVillageOfThePeopleService {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val address = Address(Street("Lady Supreme Street", 1), City("Flowerpower DC", "Holdemup"))
            val me = Person(
                "Barbara Stonewater", Age(19),
                address
            )
            val raccoonOneYearOlder = Person.age.age.modify(me) { it + 1 }
            val newAddress = Address(Street("Emancipation Street", null), City("Flowerpower DC", "Holdemup"))
            val meAfterMoving = Person.address.set(me, newAddress)

            println(me)
            println(raccoonOneYearOlder)
            println(newAddress)
            println(meAfterMoving)
        }

        fun List<User>.happyBirthday() =
            map {
                User.person.age.age.modify(it) { age ->
                    age + 1
                }
            }

        fun Person.happyBirthday(): Person =
            Person.age.age.modify(this) { it + 1 }
        fun Person.realHappyBirthDay(): Person =
            Person.age.modify(this) { Age(it.age + 1) }
    }
}