import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependency.Versions.kotlin
    id("org.jlleitschuh.gradle.ktlint") version Dependency.Versions.ktlint
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
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        implementation(platform(Dependency.aws))
        implementation(Dependency.awsS3)

        implementation(Dependency.logback)
        implementation(Dependency.log4j)

        testImplementation(Dependency.kotestRunner)
        testImplementation(Dependency.kotestCore)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "${JavaVersion.VERSION_17}"
        }
    }
}
