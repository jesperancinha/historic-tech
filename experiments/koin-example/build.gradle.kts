plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.jesperancinha"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    val koin_version = "3.5.0"
    implementation("io.insert-koin:koin-core:$koin_version")
    val gsonVersion = "2.10.1"
    implementation("com.google.code.gson:gson:$gsonVersion")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
