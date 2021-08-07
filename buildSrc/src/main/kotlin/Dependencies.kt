
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
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
        const val kotlin_stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxAndroid}"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
        const val rxBindingAppCompat = "com.jakewharton.rxbinding4:rxbinding-appcompat:${Versions.rxBindings}"
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val rxRoom ="androidx.room:room-rxjava3:${Versions.room}"
        const val roomAnnotationProcessor = "androidx.room:room-compiler:${Versions.room}"
    }

    object Test {
        const val junit_jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}"
        const val junit_jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}"
        const val junit_testRunner = "de.mannodermaus.junit5:android-test-runner:${Versions.testCore}"
        const val junit_testCore = "de.mannodermaus.junit5:android-test-core:${Versions.testCore}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    }
}
