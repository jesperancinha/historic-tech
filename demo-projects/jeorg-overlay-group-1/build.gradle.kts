allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.jesperancinha.omni)
    application
    idea
    jacoco
}

idea {
    module {
        generatedSourceDirs.add(file("build/generated/ksp/main/kotlin"))
        generatedSourceDirs.add(file("build/generated/ksp/test/kotlin"))

        sourceDirs.add(file("build/generated/ksp/main/kotlin"))
        testSources.from(file("build/generated/ksp/test/kotlin"))
    }
}

dependencies {
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.engine)
    testImplementation(libs.junit.platform.launcher)
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
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
    jvmToolchain(25)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
