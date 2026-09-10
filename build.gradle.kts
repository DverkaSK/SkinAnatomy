plugins {
    kotlin("jvm") version "2.4.0"
    `java-library`
    id("com.vanniktech.maven.publish") version "0.37.0"
}

group = "io.github.dverkask"
version = "2.1.1"
description = "A framework-agnostic Kotlin library for editing and composing Minecraft player skins."

repositories {
    mavenCentral()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
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

// Maven Central: io.github.dverkask:skinanatomy. The plugin adds the sources and javadoc jars
// Central insists on. Credentials and the signing key live in ~/.gradle/gradle.properties,
// never in this repository:
//   mavenCentralUsername, mavenCentralPassword            - a Central Portal user token
//   signingInMemoryKey, signingInMemoryKeyPassword        - an ASCII-armoured GPG key
//
// automaticRelease = false: a Central release can never be deleted, so an upload only stages
// the deployment, and a human presses "Publish" at https://central.sonatype.com/publishing.
mavenPublishing {
    coordinates(group.toString(), "skinanatomy", version.toString())
    publishToMavenCentral(automaticRelease = false)

    // Signing only when a key is configured, so a plain build, publishToMavenLocal and
    // JitPack keep working without one. Central rejects an unsigned upload on its own.
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }

    pom {
        name.set("SkinAnatomy")
        description.set(project.description)
        inceptionYear.set("2023")
        url.set("https://github.com/DverkaSK/SkinAnatomy")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("DverkaSK")
                name.set("DverkaSK")
                url.set("https://github.com/DverkaSK")
            }
        }
        scm {
            url.set("https://github.com/DverkaSK/SkinAnatomy")
            connection.set("scm:git:https://github.com/DverkaSK/SkinAnatomy.git")
            developerConnection.set("scm:git:ssh://git@github.com/DverkaSK/SkinAnatomy.git")
        }
    }
}
