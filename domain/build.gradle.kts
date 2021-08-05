plugins {
    id(Plugins.LIBRARY)
    id(Plugins.BUILD)
    id(Plugins.KAPT)
    id(Plugins.KOTLIN)
}

addTestingDependencies()
addRxDependencies()
addDatabaseDependencies()
