buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}


plugins {
    jacoco
    id( "org.jesperancinha.plugins.omni") version "0.3.1"
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
