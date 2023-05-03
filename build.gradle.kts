plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "dev.yanisk"
version = "1.0.1"


repositories {
    mavenCentral()

}
dependencies {
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.2")
    testImplementation("org.junit.platform:junit-platform-runner:1.9.2")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2.5")
    type.set("IC") // Target IDE Platform
    plugins.set(listOf("git4idea"))
}



tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    test {
        useJUnitPlatform()
    }
}