import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    val springBootVersion = "3.0.4"
    val springDependencyManagerVersion = "1.1.0"
    val graalvmBuildToolsVersion = "0.9.20"
    val kotlinPluginVersion = "1.8.10"

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

val ktlint: Configuration by configurations.creating

val ktlintVersion = "0.48.2"
val flywayVersion = "9.15.1"
val testcontainersVersion = "1.17.6"

dependencies {

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Data
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    runtimeOnly("org.postgresql:postgresql")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Others
    ktlint("com.pinterest:ktlint:$ktlintVersion") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt", "*.kts")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)
    jvmArgs = listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED")

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt", "*.kts")
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
