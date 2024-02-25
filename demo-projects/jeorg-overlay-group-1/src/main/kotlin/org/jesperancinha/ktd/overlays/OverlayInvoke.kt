package org.jesperancinha.ktd.overlays

class Bank {

    private val name: String

    constructor() {
        this.name = "Default bank name"
    }

    constructor(name: String) {
        this.name = name
    }

    companion object {
        operator fun invoke() = println("Welcome to this great bank!")
        operator fun invoke(message: String) = println("The bank welcomes you with $message")
        operator fun invoke(message: String, times: Long) = println("The bank welcomes you with $message $times times")

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            println(invoke())
            println(Bank())
            println(Bank("bankruptcy"))
            println(Bank("bankruptcy", 2))
            println("Can we make a ${Bank()}?")
        }
    }
}