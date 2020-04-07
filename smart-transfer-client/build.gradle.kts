java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":smart-transfer-server"))

    implementation(kotlin("stdlib"))
}