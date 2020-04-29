java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("multiplatform")
}


kotlin {
    linuxX64 {
        binaries {
            executable("smart-transfer-client") {
                entryPoint = "com.rmleme.smart.transfer.client.main"
            }
        }
    }

    sourceSets {
        val linuxX64Main by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))
            }
        }

        val linuxX64Test by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5-jvm:4.0.1")
                implementation("io.kotest:kotest-assertions-core-jvm:4.0.1")
            }
        }
    }
}