plugins {
    application
    jacoco
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
}
//
//sourceSets {
//    main {
//        kotlin {
//            srcDir("src/main")
//        }
//    }
//    test {
//        kotlin {
//            srcDir("src/test")
//        }
//    }
//}

tasks.test {
    useJUnitPlatform()
}
kotlin { sourceSets.all { languageSettings { languageVersion = "2.0" } } }
