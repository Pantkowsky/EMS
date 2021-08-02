package internals

/**
 * Object that defines current versions of all dependencies used in the project.
 * All versions are exported here specifically to avoid dependency version conflicts
 * and to allow for easy project-wide dependency version bump.
 */
internal object Versions {
    const val appCompat = "1.2.0"
    const val core = "1.3.2"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val junit = "4.13.2"
    const val androidx_junit = "1.1.2"
}