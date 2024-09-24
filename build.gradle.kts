buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    jacoco
    alias(libs.plugins.jesperancinha.omni)
    alias(libs.plugins.kotlin.jvm)
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }
}