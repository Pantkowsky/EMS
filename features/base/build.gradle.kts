plugins {
    id(Plugins.LIBRARY)
    id(Plugins.BUILD)
    id(Plugins.JUNIT)
    id(Plugins.KOTLIN)
}

addRxDependencies()

dependencies {
    add(Scope.IMPLEMENTATION, Dependencies.Impl.koin)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.navigation)
    add(Scope.IMPLEMENTATION, Dependencies.Impl.navigationUi)
}
