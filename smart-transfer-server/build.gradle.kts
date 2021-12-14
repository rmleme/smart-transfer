java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation(platform("com.amazonaws:aws-java-sdk-bom:${rootProject.extra["awsJavaSdkVersion"]}"))
    implementation("com.amazonaws:aws-java-sdk-s3")

    implementation("ch.qos.logback:logback-classic:${rootProject.extra["logbackVersion"]}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:${rootProject.extra["kotestVersion"]}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${rootProject.extra["kotestVersion"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
