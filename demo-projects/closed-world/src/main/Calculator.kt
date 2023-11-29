import java.util.*

class Add {
    companion object {
        @JvmStatic
        fun add(a: Int, b: Int) = a + b

        @JvmStatic
        fun join(a:Int, b: Int) = "$a$b"
    }
}

object Calculator {
    @kotlin.Throws(ReflectiveOperationException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val className = args[0]
        val methodName = args[1]
        val inputA = args[2].toInt()
        val inputB = args[3].toInt()
        val clazz = Class.forName(className)
        println(inputA)
        println(inputB)
        println(clazz)
        val method = clazz.getDeclaredMethod(methodName, Int::class.java, Int::class.java)
        println(method)
        val result = method
            .invoke(
                null,
                inputA,
                inputB
            )
        println(result)
    }
}
