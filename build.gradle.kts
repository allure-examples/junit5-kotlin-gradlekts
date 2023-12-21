plugins {
    kotlin("jvm") version "1.9.22"
}

tasks.withType(Wrapper::class) {
    gradleVersion = "8.5"
}

group = "com.example.junit5"
version = "1.0-SNAPSHOT"

val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"
val kotlinVersion = "1.9.22"

kotlin {
    jvmToolchain(17)
}

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

tasks.test {
    ignoreFailures = true
    useJUnitPlatform()
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}

dependencies {
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    
    testImplementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")

    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

repositories {
    mavenCentral()
}
