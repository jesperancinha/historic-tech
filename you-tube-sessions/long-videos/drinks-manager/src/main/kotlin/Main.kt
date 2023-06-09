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

    /**
     * We force a cast to BOX for the purpose of the example
     * Ideally this would return a BOX out of the database or perhaps
     * Making the class abstract would be a better solution
     */
    @Suppress("UNCHECKED_CAST")
    fun getBox(createBox: (Drink) -> Box): BOX = createBox(
        requireNotNull(database.remove(database.keys.last()))) as BOX
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