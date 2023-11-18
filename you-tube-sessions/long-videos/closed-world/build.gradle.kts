plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("jacoco")
}

group = "org.jesperancinha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main")
    }
}


dependencies {
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("MainKt")
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
