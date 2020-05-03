java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("com.amazonaws:aws-java-sdk-s3:${rootProject.ext["awsJavaSdkVersion"]}")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:${rootProject.ext["kotestVersion"]}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${rootProject.ext["kotestVersion"]}")
}