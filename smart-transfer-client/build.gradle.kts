import org.gradle.jvm.tasks.Jar

java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation(platform("com.amazonaws:aws-java-sdk-bom:${rootProject.extra["awsJavaSdkVersion"]}"))
    implementation("com.amazonaws:aws-java-sdk-s3")

    implementation("ch.qos.logback:logback-classic:${rootProject.extra["logbackVersion"]}")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:${rootProject.extra["kotestVersion"]}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${rootProject.extra["kotestVersion"]}")
}

tasks {
    withType<Jar> {
        manifest {
            attributes["Main-Class"] = "org.rmleme.smart.transfer.client.ClientKt"
        }

        from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}
