plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "org.jesperancinha"
version = "unspecified"

repositories {
    mavenCentral()
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

tasks.test {
    useJUnitPlatform()
}