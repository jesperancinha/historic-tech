
import org.gradle.api.JavaVersion.VERSION_24
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
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
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation(libs.crac)
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile>().configureEach {
	compilerOptions {
		freeCompilerArgs.set(listOf("-Xjsr305=strict"))
		jvmTarget.set(JVM_25)
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}
