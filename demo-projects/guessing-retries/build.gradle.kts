plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.jesperancinha.asnsei.guessing"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    api(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotest.core)
}