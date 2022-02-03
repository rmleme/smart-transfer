import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    extra["awsJavaSdkVersion"] = "1.12.128"
    extra["kotestVersion"] = "5.0.2"
    extra["logbackVersion"] = "1.2.8"
    extra["log4jVersion"] = "2.17.1"
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.rmleme.smart.transfer"
    version = "0.0.1"
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        implementation("org.apache.logging.log4j:log4j-api:${rootProject.extra["log4jVersion"]}")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "${JavaVersion.VERSION_17}"
        }
    }
}
