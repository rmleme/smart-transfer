import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val awsJavaSdkVersion by extra { "1.11.805" }
    val kotestVersion by extra { "4.0.6" }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1" apply false
    kotlin("jvm") version "1.3.72" apply false
    kotlin("plugin.spring") version "1.3.72" apply false
    id("org.springframework.boot") version "2.3.1.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
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
