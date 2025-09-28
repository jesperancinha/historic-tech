
import org.gradle.api.JavaVersion.VERSION_24
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotest_version:String by project

plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.kotlin.jvm)
	jacoco
	// Removed on purpose because we want to check what happens behind the scenes without the plugin
	// alias(libs.plugins.kotlin.spring)
}

group = "org.jesperancinha"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = VERSION_24
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

tasks.withType<KotlinCompile>().configureEach {
	compilerOptions {
		freeCompilerArgs.set(listOf("-Xjsr305=strict"))
		jvmTarget.set(JVM_24)
	}
}


kotlin {
	jvmToolchain(24)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val gradleSysVersion = System.getenv("GRADLE_VERSION")

tasks.register<Wrapper>("wrapper") {
	gradleVersion = gradleSysVersion
}
