plugins {
    alias(libs.plugins.kotlin.jvm)
    jacoco
}

group = "org.jesperancinha"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    val koin_version = "4.1.1"
    implementation("io.insert-koin:koin-core:$koin_version")
    val gsonVersion = "2.13.1"
    implementation("com.google.code.gson:gson:$gsonVersion")
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
