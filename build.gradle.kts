import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	val springBootVersion = "3.0.1"
	val springDependencyManagerVersion = "1.1.0"
	val graalvmBuildToolsVersion = "0.9.19"
	val kotlinPluginVersion = "1.8.0"

	id("org.springframework.boot") version springBootVersion
	id("io.spring.dependency-management") version springDependencyManagerVersion
	id("org.graalvm.buildtools.native") version graalvmBuildToolsVersion
	kotlin("jvm") version kotlinPluginVersion
	kotlin("plugin.spring") version kotlinPluginVersion
	kotlin("plugin.jpa") version kotlinPluginVersion
}

group = "io.moya"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<BootBuildImage>("bootBuildImage") {
	tags.add("ghcr.io/moyaf/la-la-land-or-nicky-minaj:latest")
	docker {
		publishRegistry {
			username.set("MoyaF")
			password.set(System.getenv("DOCKER_REGISTRY_PASSWORD") ?: "")
		}
	}
}