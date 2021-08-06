buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.7.1.1")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply {
        from("${project.rootDir}/linters/ktlint.gradle")
    }
}

tasks.apply {
    create<Delete>("clean") {
        delete(rootProject.buildDir)
    }
}

detekt {
    toolVersion = Versions.detekt
    buildUponDefaultConfig = true
    debug = true

    input = files(
        "src/main/java",
        "src/main/kotlin"
    )
    config = files("$projectDir/linters/detekt.yml")

    reports {
        html {
            enabled = true
            destination = file("build/reports/detekt.html")
        }
        txt.enabled = true
        sarif.enabled = true
    }
}
