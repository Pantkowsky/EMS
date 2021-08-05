/**
 * Object that encapsulates the scope type for [Dependencies] block in
 * gradle build files
 */
object Scope {
    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
    const val TEST = "testImplementation"
    const val TEST_ANDROID = "androidTestImplementation"
}
