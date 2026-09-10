plugins {
    kotlin("jvm") version "2.4.0"
    `java-library`
    `maven-publish`
}

group = "ru.dverkask"
version = "2.1.0"
description = "A framework-agnostic Kotlin library for editing and composing Minecraft player skins."

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveBaseName.set("SkinAnatomy")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("SkinAnatomy")
                description.set(project.description)
                url.set("https://github.com/DverkaSK/SkinAnatomy")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }

    // No remote repository is configured out of the box - `publishToMavenLocal` works
    // immediately for local consumption; add a `repositories { maven { ... } }` block
    // here (e.g. GitHub Packages) once you have somewhere to publish releases to.
}
