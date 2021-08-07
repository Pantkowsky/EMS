import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.addBaseAndroidDependencies() {
    dependencies {
        add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_appCompat)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_core)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_constraintLayout)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_material)
    }
}

fun Project.addRxDependencies() {
    dependencies {
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxAndroid)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxJava)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxKotlin)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxBindingAppCompat)
    }
}

fun Project.addDatabaseDependencies() {
    dependencies {
        add(Scope.IMPLEMENTATION, Dependencies.Impl.room)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxRoom)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.gson)
        add(Scope.KAPT, Dependencies.Impl.roomAnnotationProcessor)
    }
}

fun Project.addFeatureBase() {
    dependencies {
        add(Scope.IMPLEMENTATION, project(":features:base"))
        add(Scope.IMPLEMENTATION, Dependencies.Impl.koin)
    }
    addBaseAndroidDependencies()
    addRxDependencies()
}
