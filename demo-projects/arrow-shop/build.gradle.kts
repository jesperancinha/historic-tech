plugins {
    alias(libs.plugins.jesperancinha.omni)
    jacoco
}

group = "org.jesperancinha"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.resilience4j.retry)
    implementation(libs.resilience4j.kotlin)
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)
    testImplementation(libs.kotest.core)
}

tasks.test {
    useJUnitPlatform()
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.register("prepareKotlinBuildScriptModel"){}
