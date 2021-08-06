
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
        const val junit_jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}"
        const val junit_jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}"
        const val junit_testRunner = "de.mannodermaus.junit5:android-test-runner:${Versions.testCore}"
        const val junit_testCore = "de.mannodermaus.junit5:android-test-core:${Versions.testCore}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    }
}
