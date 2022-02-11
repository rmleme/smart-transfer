object Dependency {

    object Versions {
        // Kotlin
        const val kotlin = "1.6.10"

        // AWS
        const val aws = "1.12.128"

        // Spring
        const val springBoot = "2.6.1"
        const val springPlugin = "1.6.10"
        const val springDependencyMngmt = "1.0.11.RELEASE"

        // Logs
        const val logback = "1.2.8"
        const val log4j = "2.17.1"

        // Tests
        const val kotest = "5.0.2"

        const val ktlint = "10.2.0"
    }

    // AWS
    const val aws = "com.amazonaws:aws-java-sdk-bom:${Versions.aws}"
    const val awsS3 = "com.amazonaws:aws-java-sdk-s3"

    // Spring
    const val springBoot = "org.springframework.boot:spring-boot-starter-web"
    const val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // Logs
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val log4j = "org.apache.logging.log4j:log4j-api:${Versions.log4j}"

    // Test
    const val kotestRunner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
    const val kotestCore = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val springTest = "org.springframework.boot:spring-boot-starter-test"

    const val ktlint = "org.jlleitschuh.gradle.ktlint:${Versions.ktlint}"
}
