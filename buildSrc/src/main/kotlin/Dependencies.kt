import internals.Versions

/**
 * Class that encapsulates all dependency definitions used by this project.
 * Stored here specifically to allow for easier global dependency management.
 */
sealed class Dependencies {

    object Impl {
        const val androidx_appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val androidx_core = "androidx.core:core-ktx:${Versions.core}"
        const val androidx_material = "com.google.android.material:material:${Versions.material}"
        const val androidx_constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val kotlin_stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }

    object Test {
        const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
        const val junit = "junit:junit:${Versions.junit}"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }
}
