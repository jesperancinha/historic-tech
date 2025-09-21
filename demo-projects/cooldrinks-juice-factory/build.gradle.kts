plugins {
    application
    jacoco
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
    jvmToolchain(25)
}

application {
    mainClass.set("MainKt")
}

val gradleSysVersion: String? = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
    gradleVersion = gradleSysVersion
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
