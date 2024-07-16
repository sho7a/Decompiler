import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("application")
    id("java")
    id("com.github.johnrengelman.shadow") version("8.1.1")
    id("io.freefair.lombok") version("8.6")
    kotlin("jvm") version("2.0.0")
}

group = "com.sotasan"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin.compilerOptions {
    jvmTarget = JvmTarget.JVM_11
}

application {
    mainClass = "${project.group}.${project.name.lowercase()}.MainKt"
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://maven.fabricmc.net") }
}

dependencies {
    implementation("com.fifesoft:rsyntaxtextarea:3.5.0")
    implementation("com.formdev:flatlaf:3.4.1")
    implementation("com.formdev:flatlaf-extras:3.4.1")
    implementation("com.formdev:flatlaf-fonts-inter:4.0")
    implementation("com.formdev:flatlaf-fonts-jetbrains-mono:2.304")
    implementation("com.github.java-decompiler:jd-core:v1.1.3")
    implementation("com.miglayout:miglayout-swing:11.4")
    implementation("net.fabricmc:cfr:0.2.2")
    implementation("org.bitbucket.mstrobel:procyon-compilertools:0.6.0")
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("org.vineflower:vineflower:1.10.1")
}

tasks {
    processResources {
        outputs.upToDateWhen { false }
        filesMatching("application.properties") {
            expand(project.properties)
        }
    }

    jar {
        enabled = false
    }

    shadowJar {
        dependsOn(distTar, distZip)
        archiveBaseName = project.name.lowercase()
        archiveClassifier = null
    }

    test {
        enabled = false
    }

    register<Jar>("testJar") {
        archiveFileName = "test.jar"
        from(sourceSets.test.get().output)
    }
}