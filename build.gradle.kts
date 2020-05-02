import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    repositories {
        jcenter()
    }

    group = "com.rmleme.smart.transfer"
    version = "0.0.1"
}

plugins {
    kotlin("jvm") apply false
}

subprojects {
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