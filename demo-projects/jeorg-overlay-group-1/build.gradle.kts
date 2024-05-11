allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
//    id("io.arrow-kt.analysis.kotlin") version "2.0.2"
    alias(libs.plugins.kotlin.jvm)
    application
    idea
    jacoco
    id("org.jesperancinha.plugins.omni") version "0.3.1"
}

idea {
    module {
        sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin")
        testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
        generatedSourceDirs =
            generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
    }
}
val arrowVersion = "1.1.5"

dependencies {
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.9.2")

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
    jvmToolchain(19)
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}
