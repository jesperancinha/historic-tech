plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    jacoco
}

group = "boat-repair-service"
version = "0.0.0"

kotlin {
    jvm {
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
    }
}
