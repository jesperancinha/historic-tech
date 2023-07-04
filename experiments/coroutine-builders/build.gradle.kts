plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "org.jesperancinha.experiments"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec> {
    jvmArgs ("--enable-preview")
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("MainKt")
}
task("runWithJavaExec", JavaExec::class) {
    mainClass.set("MainJava")
    classpath = files(tasks.jar)
    jvmArgs ("--enable-preview")
}