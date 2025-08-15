plugins {
    id("org.jetbrains.dokka")
    kotlin("multiplatform")
    jacoco
}

group = "car-repair-service"
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
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
            }
        }
        val jvmMain by getting
        val jvmTest by getting
    }
}
