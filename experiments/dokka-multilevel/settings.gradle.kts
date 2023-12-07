pluginManagement {
    val kotlinVersion: String by settings
    val dokkaVersion: String by settings

    plugins {
        kotlin("multiplatform") version kotlinVersion
        id("org.jetbrains.dokka") version dokkaVersion
    }
}

include("boat-repair-service")
include("motorway-repair-service:car-repair-service")

rootProject.name = "dokka-multilevel"
