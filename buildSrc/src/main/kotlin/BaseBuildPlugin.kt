import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class BaseBuildPlugin : Plugin<Project> {

    private companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    override fun apply(project: Project) {
        if (project.hasProperty("android")) {
            with(project) {
                configureCommons()
                configureCommonDependencies()
            }
        }
    }

    private fun Project.configureCommons() {
        extensions.getByType<BaseExtension>().run {
            configureVersions()
            configureExtensions()
            configureCompileOptions()
            configureDefault()
            configureKotlinTasks()
            configureViewBinding()
        }
    }

    private fun BaseExtension.configureBuildTypes() {
        buildTypes {
            getByName(RELEASE) {
                isMinifyEnabled = true
                isShrinkResources = true
                resValue("string", "app_name", "EMS")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName(DEBUG) {
                applicationIdSuffix(".dev")
                versionNameSuffix("-dev")
                resValue("string", "app_name", "EMS-dev")
                splits {
                    abi.isEnable = true
                    density.isEnable = true
                }
                isCrunchPngs = false
                isTestCoverageEnabled = true
            }
            /*
                any other build type configurations (i.e staging) are created
                and configured here
             */
        }
    }

    private fun Project.configureCommonDependencies() {
        extensions.getByType<BaseExtension>().run {
            dependencies {
                add(Scope.IMPLEMENTATION, Dependencies.Impl.kotlin_stdLib)
                add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_core)
                addTestingDependencies()
            }
        }
    }

    private fun BaseExtension.configureVersions() {
        compileSdkVersion(Config.SDK_VERSION)
        buildToolsVersion(Config.TOOLS_VERSION)
    }

    private fun BaseExtension.configureExtensions() {
        when(this) {
            is AppExtension -> configureBuildTypes()
            is LibraryExtension -> defaultConfig {
                consumerProguardFile("proguard-rules.pro")
            }
        }
    }

    private fun BaseExtension.configureCompileOptions() =
        compileOptions {
            sourceCompatibility = Config.java
            targetCompatibility = Config.java
        }

    private fun BaseExtension.configureDefault() =
        defaultConfig {
            minSdk = Config.SDK_MIN
            targetSdk = Config.SDK_TARGET
            versionCode = Config.VERSION_CODE
            versionName = Config.VERSION_NAME
            testInstrumentationRunner = Config.TEST_RUNNER
            setTestInstrumentationRunnerArguments(mutableMapOf(
                "runnerBuilder" to "de.mannodermaus.junit5.AndroidJUnit5Builder"
            ))
        }

    private fun BaseExtension.configureViewBinding() {
        buildFeatures.viewBinding = true
    }

    private fun Project.configureKotlinTasks() =
        tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                jvmTarget = Config.JVM
            }
        }

    private fun DependencyHandlerScope.addTestingDependencies() =
        this.apply {
            add(Scope.TEST, Dependencies.Test.junit_jupiterApi)
            add(Scope.TEST, Dependencies.Test.junit_jupiterEngine)
            add(Scope.TEST_ANDROID, Dependencies.Test.junit_jupiterApi)
            add(Scope.TEST_RUNTIME, Dependencies.Test.junit_testRunner)
            add(Scope.TEST_ANDROID, Dependencies.Test.junit_testCore)
            add(Scope.TEST_ANDROID, Dependencies.Test.testRunner)
        }
}
