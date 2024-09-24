plugins {
    alias(libs.plugins.jesperancinha.omni)
    jacoco
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

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.register("prepareKotlinBuildScriptModel"){}
