import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("base-build-plugin") {
            id = "base-build-plugin"
            implementationClass = "BaseBuildPlugin"
        }
    }
}

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:7.0.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
}