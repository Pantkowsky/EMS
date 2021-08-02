buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.apply {
    create<Delete>("clean") {
        delete(rootProject.buildDir)
    }
}
