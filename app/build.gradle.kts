plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.pantkowski.ems"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    add("implementation", "androidx.core:core-ktx:1.3.2")
    add("implementation", "androidx.appcompat:appcompat:1.2.0")
    add("implementation", "com.google.android.material:material:1.3.0")
    add("implementation", "androidx.constraintlayout:constraintlayout:2.0.4")
    add("testImplementation", "junit:junit:4.13.2")
    add("androidTestImplementation", "androidx.test.ext:junit:1.1.2")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.3.0")
}