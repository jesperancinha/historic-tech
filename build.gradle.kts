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

    tasks.withType<org.gradle.api.tasks.testing.Test> {
        // Use JUnit Platform for all test tasks and avoid failing when no tests are discovered
        useJUnitPlatform()
        failOnNoDiscoveredTests = false
    }
}