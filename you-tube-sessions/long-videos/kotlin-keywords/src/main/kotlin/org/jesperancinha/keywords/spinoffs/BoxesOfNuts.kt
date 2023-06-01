package org.jesperancinha.keywords.spinoffs


abstract class Nut

class Almond : Nut()
class Macadamia : Nut()

class Pistache : Nut()

// Switch this line by the next one and check out why without `out`, this doesn't work.
//abstract class Box<T : Nut>(
abstract class Box<out T : Nut>(
) {
    abstract fun removeNuts(number: Int): List<T>
}

class OutPinkBox() : Box<Almond>() {

    val nuts = (1..10).map { Almond() }.toMutableList()

    override fun removeNuts(number: Int): MutableList<Almond> =
        nuts.subList(0, 3).also { nuts.removeAll(it) }
}

class OutBlueBox() : Box<Macadamia>() {
    val nuts = (1..10).map { Macadamia() }.toMutableList()
    override fun removeNuts(number: Int): MutableList<Macadamia> =
        nuts.subList(0, 3)

}

class OutGreenBox(
) : Box<Pistache>() {

    val nuts: MutableList<Pistache> = (1..10).map { Pistache() }.toMutableList()

    override fun removeNuts(number: Int): MutableList<Pistache> =
        nuts.subList(0, 3)

}

fun main() {
    val pinkBox = OutPinkBox()
    val blueBox = OutBlueBox()
    val greenBox = OutGreenBox()

    removeNuts(nNuts = 3, box = pinkBox)
    removeNuts(nNuts = 3, box = blueBox)
    removeNuts(nNuts = 3, box = greenBox)
}

fun removeNuts(nNuts: Int, box: Box<Nut>) = box.removeNuts(3)
