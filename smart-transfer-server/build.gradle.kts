java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:${rootProject.ext["kotestVersion"]}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${rootProject.ext["kotestVersion"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}