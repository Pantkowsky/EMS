import org.gradle.api.JavaVersion

/**
 * Object that specifies all project configuration constants and their versions
 */
object Config {
    const val ID = "com.pantkowski.ems"
    const val JVM = "1.8"
    const val SDK_MIN = 26
    const val SDK_VERSION = 30
    const val SDK_TARGET = 30
    const val TOOLS_VERSION = "30.0.3"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    val java = JavaVersion.VERSION_1_8
}