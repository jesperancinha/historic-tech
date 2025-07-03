buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}


plugins {
    jacoco
    alias(libs.plugins.jesperancinha.omni)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
