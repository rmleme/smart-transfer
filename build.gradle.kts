import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    group = "com.rmleme.smart.transfer"
    version = "0.0.1"
}

plugins {
    kotlin("jvm") apply false
}

subprojects {
    ext["awsJavaSdkVersion"] = project.findProperty("awsJavaSdkVersion") as String
    ext["kotestVersion"] = project.findProperty("kotestVersion") as String

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