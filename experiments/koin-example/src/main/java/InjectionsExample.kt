import GsonComponent.deserialize
import GsonComponent.serialize
import UserComponent.givePresent
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module


class XmasService {
    fun givePresent(user: User) {
        println("Present given to $user")
    }

}

val xmasModule = module {
    single { XmasService() }
    single { Gson() }
}

data class User(
    val name: String,
    val state: String
)

object UserComponent : KoinComponent {

    private val xmasService: XmasService by inject()

    fun User.givePresent() = xmasService.givePresent(this)
}


object GsonComponent : KoinComponent {

    private val gson: Gson by inject()

    fun <T> String.deserialize(type: Class<T>): T = gson.fromJson(this, type)

    fun <T> T.serialize(): String = gson.toJson(this)

}

fun main(vararg args: String) {
    startKoin {
        modules(xmasModule)
    }
    User(name = "Wow", state = "Fantastic").givePresent()
    println(User(name = "OhHO!", state = "Fabulous").serialize())
    println("{\"name\":\"OhHO!\",\"state\":\"Fabulous\"}".deserialize(User::class.java))
}
