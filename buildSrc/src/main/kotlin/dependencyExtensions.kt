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

fun Project.addTestingDependencies() {
    dependencies {
        add(Scope.TEST, Dependencies.Test.androidx_junit)
        add(Scope.TEST, Dependencies.Test.junit)
        add(Scope.TEST, Dependencies.Test.kotlinTest)
        add(Scope.TEST, Dependencies.Test.kotlinTest)
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
        add(Scope.KAPT, Dependencies.Impl.roomAnnotationProcessor)
        add(Scope.IMPLEMENTATION, Dependencies.Impl.rxBindingAppCompat)
    }
}
