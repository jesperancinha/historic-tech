pluginManagement {
    val kotlinVersion: String by settings
    val dokkaVersion: String by settings

    plugins {
        kotlin("multiplatform") version kotlinVersion
        id("org.jetbrains.dokka") version dokkaVersion
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Use the parent repository's central version catalog
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}

include("boat-repair-service")
include("motorway-repair-service:car-repair-service")

rootProject.name = "dokka-multilevel"
