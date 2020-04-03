plugins {
    kotlin("jvm")
}

dependencies {
    compile(project(":smart-transfer-server"))

    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.1")
}