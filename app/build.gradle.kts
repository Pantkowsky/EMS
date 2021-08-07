plugins {
    id(Plugins.APPLICATION)
    id(Plugins.BUILD)
    id(Plugins.KOTLIN)
}

android {
    defaultConfig {
        applicationId = Config.ID
    }
}

dependencies {
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_appCompat)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_core)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_material)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.androidx_constraintLayout)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.koin)
}
