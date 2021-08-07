plugins {
    id(Plugins.LIBRARY)
    id(Plugins.BUILD)
    id(Plugins.JUNIT)
    id(Plugins.KAPT)
    id(Plugins.KOTLIN)
}

addRxDependencies()
addDatabaseDependencies()
