import org.gradle.api.JavaVersion.VERSION_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotest_version:String by project

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	// Removed on purpose because we want to check what happens behind the scenes without the plugin
	// kotlin("plugin.spring") version "1.9.21"
}

group = "org.jesperancinha"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-assertions-core-jvm:$kotest_version")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

kotlin {
	jvmToolchain(21)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
