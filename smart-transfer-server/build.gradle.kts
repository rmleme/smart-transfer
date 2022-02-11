plugins {
    kotlin("plugin.spring") version Dependency.Versions.springPlugin
    id("org.springframework.boot") version Dependency.Versions.springBoot
    id("io.spring.dependency-management") version Dependency.Versions.springDependencyMngmt
}

apply(plugin = "kotlin-spring")

dependencies {
    implementation(Dependency.springBoot)
    implementation(Dependency.jackson)

    testImplementation(Dependency.springTest) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
