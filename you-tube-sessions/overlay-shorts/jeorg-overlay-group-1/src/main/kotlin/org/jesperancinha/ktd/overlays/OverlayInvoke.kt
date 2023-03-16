package org.jesperancinha.ktd.overlays

class Bank {
    companion object {
        operator fun invoke() = println("Welcome to this great bank!")
        operator fun invoke(message: String) = println("The bank welcomes you with $message")

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            Bank()
            Bank("bankruptcy")
            println("Can we make a ${Bank()}?")
        }
    }
}