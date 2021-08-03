buildscript {
    repositories {
        google()
        mavenCentral()
    }
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
