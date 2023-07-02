val kotlin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.2"
    id("jacoco")
}

group = "org.jesperancinha"
version = "0.0.1"
application {
    mainClass.set("org.jesperancinha.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}