plugins {
    id(Plugins.APPLICATION)
    id(Plugins.KOTLIN)
}

android {
    compileSdk = Config.SDK_VERSION

    defaultConfig {
        applicationId = Config.ID
        minSdk = Config.SDK_MIN
        targetSdk = Config.SDK_TARGET
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME
        testInstrumentationRunner = Config.TEST_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = Config.java
        targetCompatibility = Config.java
    }
    kotlinOptions {
        jvmTarget = Config.JVM
    }
}

dependencies {
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_appCompat)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_core)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_material)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_constraintLayout)
    add(Scope.TEST, Dependencies.Test.junit)
    add(Scope.TEST_ANDROID, Dependencies.Test.androidx_junit)
}