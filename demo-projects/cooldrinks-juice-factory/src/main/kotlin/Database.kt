import java.util.*
import kotlin.random.Random

const val FIFTY_MILLION = 50_000_000

open class Drink<T : Number>(
    open val serialNumber: UUID,
    open val quantity: T
)

data class Juice(
    override val serialNumber: UUID,
    override val quantity: Long
) : Drink<Long>(serialNumber, quantity)

abstract class Database<in QUANTITY : Number, out DRINK : Drink<Long>> {
    abstract fun addQuantityToDatabase(quantity: QUANTITY)

    abstract fun takeFirstDrink(): DRINK
}

class JuiceDatabase(private val serialNumber: UUID) : Database<Long, Juice>() {

    val content by lazy { mutableListOf<Juice>() }


    init {
        repeat((FIFTY_MILLION)) {
            content.add(Juice(serialNumber, Random.nextLong(990, 1010)))
        }
    }

    override fun addQuantityToDatabase(quantity: Long): Unit = content.add(
        Juice(
            serialNumber = serialNumber,
            quantity = quantity
        )
    )
        .takeIf { it }
        ?.let { } ?: throw RuntimeException("Could not add element with quantity $quantity to the database")

    override fun takeFirstDrink(): Juice = content.removeAt(0)
}
