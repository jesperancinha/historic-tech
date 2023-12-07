plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
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

tasks.dokkaHtmlMultiModule {
    moduleName.set("Dokka MultiModule Example")
    dependsOn(":motorway-repair-service:dokkaHtmlMultiModule")
}