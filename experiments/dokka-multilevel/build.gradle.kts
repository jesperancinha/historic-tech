plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    jacoco
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    apply(plugin = "org.jetbrains.dokka")
}

// Provide a conventional `test` task so `./gradlew test` works on this multi-module KMP build
// Root-level aggregate `test` depends on each subproject's `check` lifecycle task,
// which in turn depends on the appropriate test tasks (e.g., jvmTest) when present.
tasks.register("test") {
    group = "verification"
    description = "Runs tests in all subprojects."
    dependsOn(subprojects.map { "${it.path}:check" })
}

// Also expose a `test` task inside each subproject that runs its JVM tests when available.
subprojects {
    tasks.register("test") {
        group = "verification"
        description = "Runs JVM tests for this project."
        // Only triggers if the project defines a jvmTest task (common for KMP with JVM target)
        dependsOn(tasks.matching { it.name == "jvmTest" })
        // Fallback to `check` so the task still succeeds even if there is no explicit jvmTest
        dependsOn(tasks.matching { it.name == "check" })
    }
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Dokka MultiModule Example")
    dependsOn(":motorway-repair-service:dokkaHtmlMultiModule")
}
