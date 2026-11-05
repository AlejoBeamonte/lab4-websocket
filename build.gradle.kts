plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.ktlint)
}

group = "es.unizar.webeng"
version = "2026-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

dependencies {
    val springBootVersion = libs.versions.springBoot.get()
    val springBootBom = platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    implementation(springBootBom)

    implementation(libs.spring.boot.starter.websocket)
    implementation(libs.kotlin.logging)

    testImplementation(libs.spring.boot.starter.websocket.test)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
}
