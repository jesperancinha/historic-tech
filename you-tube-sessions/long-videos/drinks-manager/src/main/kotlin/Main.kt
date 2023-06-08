import java.util.UUID

open class Drink()

open class WarmDrink() : Drink()

open class ColdDrink() : Drink()

open class TeaDrink() : WarmDrink()

open class CoffeeDrink() : WarmDrink()

open class Lemonade() : ColdDrink()

open class OrangeJuice() : ColdDrink()

open class Box()

open class CardboardBox() : Box()

open class PlasticBox() : Box()

open class FamilyBox(drink: Drink) : CardboardBox()

open class DeluxeBox(drink: Drink) : PlasticBox()

open class DrinksService<in DRINK : Drink, out BOX : Box> {
    val database by lazy { HashMap<UUID, Drink>() }

    fun sendDrink(drink: DRINK) = run { database[UUID.randomUUID()] = drink }

    fun getBox(createBox: (Drink) ->  Box) = createBox(
        requireNotNull(database.remove(database.keys.last())))
}


fun checkResistenceState(drinksService: DrinksService<WarmDrink, Box>){

}

fun checkCoolerState(drinksService: DrinksService<ColdDrink, Box>){

}

fun main(args: Array<String>) {
    val coldDrinksService = DrinksService<ColdDrink, FamilyBox>()
    coldDrinksService.sendDrink(Lemonade())
    coldDrinksService.sendDrink(OrangeJuice())
    coldDrinksService.getBox { FamilyBox(it) }
    coldDrinksService.getBox { FamilyBox(it) }

    val warmeDrinksService = DrinksService<WarmDrink, DeluxeBox>()
    warmeDrinksService.sendDrink(CoffeeDrink())
    warmeDrinksService.sendDrink(TeaDrink())
    warmeDrinksService.getBox { DeluxeBox(it) }
    warmeDrinksService.getBox { DeluxeBox(it) }

    val genericDrinksService = DrinksService<Drink, CardboardBox>()

    checkResistenceState(warmeDrinksService)
    checkCoolerState(coldDrinksService)
    checkResistenceState(genericDrinksService)
    checkCoolerState(genericDrinksService)

    DrinksManagerJava.main(args)
}