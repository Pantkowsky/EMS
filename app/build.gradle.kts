plugins {
    id(Plugins.APPLICATION)
    id(Plugins.BUILD)
    id(Plugins.KOTLIN)
}

android {
    compileSdk = Config.SDK_VERSION
}

dependencies {
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_appCompat)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_core)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_material)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_constraintLayout)
    add(Scope.TEST, Dependencies.Test.junit)
    add(Scope.TEST_ANDROID, Dependencies.Test.androidx_junit)
}
