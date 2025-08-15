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
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.resilience4j.retry)
    implementation(libs.resilience4j.kotlin)
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    testImplementation(libs.kotest.core)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.register("prepareKotlinBuildScriptModel"){}
