import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    extra["awsJavaSdkVersion"] = "1.12.128"
    extra["kotestVersion"] = "5.0.2"
    extra["logbackVersion"] = "1.2.7"
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0" apply false
    kotlin("jvm") version "1.6.10" apply false
    kotlin("plugin.spring") version "1.6.10" apply false
    id("org.springframework.boot") version "2.6.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
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
            jvmTarget = "1.17"
        }
    }
}
