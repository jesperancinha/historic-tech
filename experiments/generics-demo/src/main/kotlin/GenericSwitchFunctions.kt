import Marble.*
import MarbleBag.*

enum class Marble {
    Wooden,
    Glass,
    Metal
}

sealed class MarbleBag {
    sealed class WoodenMarbleBag : MarbleBag()
    sealed class GlassMarbleBag : MarbleBag()
    sealed class MetalMarbleBag : MarbleBag()
}

fun generateMarbleBagWork(marble: Marble): () -> Unit = when (marble) {
    Wooden -> {
        { workMarbleBag<WoodenMarbleBag>() }
    }

    Glass -> {
        { workMarbleBag<GlassMarbleBag>() }
    }

    Metal -> {
        { workMarbleBag<MetalMarbleBag>() }
    }
}

inline fun <reified T> workMarbleBag() {
    println("--- Do some ${T::class.java} work---")
}

fun main() {
    val f1 = generateMarbleBagWork(Wooden)
    val f2 = generateMarbleBagWork(Glass)
    val f3 = generateMarbleBagWork(Metal)
    f1()
    f2()
    f3()
}