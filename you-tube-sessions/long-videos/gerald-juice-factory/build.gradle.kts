plugins {
    kotlin("jvm") version "1.8.0"
    application
    id("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("src/main/kotlin")
    }
}


dependencies {
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
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
