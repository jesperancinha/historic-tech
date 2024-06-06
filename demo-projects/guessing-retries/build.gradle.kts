plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jesperancinha.omni)
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
    api(libs.resilience4j)
    api(libs.resilience4j.retry)
    api(libs.resilience4j.kotlin)
    api(libs.arrow.core)
    api(libs.arrow.fx.coroutines)
    testImplementation(libs.kotest.core)
}