plugins {
    jacoco
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.jesperancinha.omni)
}

group = "org.jesperancinha.arrow.books"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    ksp(libs.arrow.optics.ksp.plugin)
    implementation(platform(libs.arrow.stack))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    implementation("io.arrow-kt:arrow-fx-coroutines")
    implementation("io.arrow-kt:arrow-resilience")
    testImplementation(kotlin("test"))
    /**
     * NOTE: Core assertions are normally part of the test scope
     * We put them bundle together with release code because of the test nature of the project
     */
    api(libs.kotest.assertions.core.jvm)

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.register("prepareKotlinBuildScriptModel"){}
