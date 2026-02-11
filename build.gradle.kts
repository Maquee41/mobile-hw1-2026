plugins {
    kotlin("jvm") version "2.1.0"
}

kotlin {
    jvmToolchain(21)
}

group = "orders"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("verifyHomework") {
    group = "verification"
    description = "Runs all homework tests to verify the implementation"
    dependsOn("test")
    doLast {
        println("SUCCESS: All tests passed! Homework is complete.")
    }
}
