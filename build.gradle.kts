allprojects {
    repositories {
        jcenter()
    }

    group = "com.rmleme.smart-transfer"
    version = "1.0.0"
}

plugins {
    kotlin("jvm") version "1.3.71" apply false
}

subprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}