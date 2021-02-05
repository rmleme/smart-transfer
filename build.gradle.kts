import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    extra["awsJavaSdkVersion"] = "1.11.949"
    extra["kotestVersion"] = "4.4.0"
    extra["logbackVersion"] = "1.2.3"
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1" apply false
    kotlin("jvm") version "1.4.30" apply false
    kotlin("plugin.spring") version "1.3.72" apply false
    id("org.springframework.boot") version "2.4.2" apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.rmleme.smart.transfer"
    version = "0.0.1"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}
