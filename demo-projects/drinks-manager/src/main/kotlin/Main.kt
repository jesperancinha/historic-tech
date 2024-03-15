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

open class DrinksService<in DRINK : Drink, out BOX : Box> {
    private val database by lazy { HashMap<UUID, Drink>() }

    fun sendDrink(drink: DRINK) = run { database[UUID.randomUUID()] = drink }

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

    val warmDrinkDeluxeBoxDrinksService = DrinksService<WarmDrink, DeluxeBox>()
    warmDrinkDeluxeBoxDrinksService.sendDrink(CoffeeDrink())
    warmDrinkDeluxeBoxDrinksService.sendDrink(TeaDrink())
    warmDrinkDeluxeBoxDrinksService.getBox { DeluxeBox(it) }
    warmDrinkDeluxeBoxDrinksService.getBox { DeluxeBox(it) }

    val genericDrinksService = DrinksService<Drink, CardboardBox>()

    checkResistenceState(warmDrinkDeluxeBoxDrinksService)
    checkCoolerState(coldDrinksService)
    checkResistenceState(genericDrinksService)
    checkCoolerState(genericDrinksService)

    DrinksManagerJava.main(args)
    org.jesperancinha.asngiasagf.variance.main(args)

    println("Done!")
}
