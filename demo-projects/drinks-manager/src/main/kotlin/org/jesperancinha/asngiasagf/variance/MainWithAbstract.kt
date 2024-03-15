package org.jesperancinha.asngiasagf.variance

import java.util.UUID

open class Drink

open class WarmDrink : Drink()

open class ColdDrink : Drink()

open class TeaDrink : WarmDrink()

open class CoffeeDrink : WarmDrink()

open class Lemonade : ColdDrink()

open class OrangeJuice : ColdDrink()

open class Box

open class CardboardBox : Box()

open class PlasticBox : Box()

open class FamilyBox(val drink: Drink) : CardboardBox()

open class DeluxeBox(val drink: Drink) : PlasticBox()

abstract class DrinksService<in DRINK : Drink, out BOX : Box> {
    protected val database by lazy { HashMap<UUID, Drink>() }

    fun sendDrink(drink: DRINK) = run { database[UUID.randomUUID()] = drink }

    abstract fun getBox(): BOX
}

open class ColdDrinksService : DrinksService<ColdDrink, FamilyBox>() {
    override fun getBox(): FamilyBox = FamilyBox(requireNotNull(database.remove(database.keys.last())))
}

open class WarmDrinksService : DrinksService<WarmDrink, DeluxeBox>() {
    override fun getBox(): DeluxeBox = DeluxeBox(requireNotNull(database.remove(database.keys.last())))
}
open class GenericDrinksService : DrinksService<Drink, DeluxeBox>() {
    override fun getBox(): DeluxeBox = DeluxeBox(requireNotNull(database.remove(database.keys.last())))
}


fun checkResistenceState(drinksService: DrinksService<WarmDrink, Box>) {

}

fun checkCoolerState(drinksService: DrinksService<ColdDrink, Box>) {

}


open class ColdDrinksFamilyBoxService : DrinksService<ColdDrink, FamilyBox>() {
    override fun getBox(): FamilyBox = FamilyBox(requireNotNull(database.remove(database.keys.last())))
}

open class ColdDrinksDeluxeBoxService : DrinksService<ColdDrink, DeluxeBox>() {
    override fun getBox(): DeluxeBox = DeluxeBox(requireNotNull(database.remove(database.keys.last())))
}

open class ColdDrinksBoxService : DrinksService<ColdDrink, Box>() {
    override fun getBox(): DeluxeBox = DeluxeBox(requireNotNull(database.remove(database.keys.last())))
}


fun main(args: Array<String>) {
    val coldDrinksService = ColdDrinksService()
    coldDrinksService.sendDrink(Lemonade())
    coldDrinksService.sendDrink(OrangeJuice())
    coldDrinksService.getBox()
    coldDrinksService.getBox()

    val warmDrinksService = WarmDrinksService()
    warmDrinksService.sendDrink(CoffeeDrink())
    warmDrinksService.sendDrink(TeaDrink())
    warmDrinksService.getBox()
    warmDrinksService.getBox()

    val genericDrinksService = GenericDrinksService()

    checkResistenceState(warmDrinksService)
    checkCoolerState(coldDrinksService)
    checkResistenceState(genericDrinksService)
    checkCoolerState(genericDrinksService)

    val coldDrinksDeluxeBoxService = ColdDrinksDeluxeBoxService()

    val coldDrinksBoxService:  DrinksService<ColdDrink, Box> = object: DrinksService<ColdDrink, FamilyBox>() {
        override fun getBox(): FamilyBox {
            TODO("Not yet implemented")
        }
    }
//    val coldDrinksFamilyBoxService:  DrinksService<ColdDrink, FamilyBox> = object: DrinksService<ColdDrink, Box>() {
//        override fun getBox(): Box {
//            TODO("Not yet implemented")
//        }
//    }

    val drinksFamilyBoxService:  DrinksService<ColdDrink, FamilyBox> = object : DrinksService<Drink, FamilyBox>() {
        override fun getBox(): FamilyBox {
            TODO("Not yet implemented")
        }
    }
//    val coldDrinksFamilyBoxService:  DrinksService<Drink, FamilyBox> = object : DrinksService<ColdDrink, FamilyBox>() {
//        override fun getBox(): FamilyBox {
//            TODO("Not yet implemented")
//        }
//    }
    DrinksManagerJava.main(args)
}