// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(ClassPaths.androidHilt)
        classpath(ClassPaths.navigation)
    }
}

plugins {
    id(ProjectLevelPlugins.androidApplication) version Versions.androidApplication apply Versions.applyFalse
    id(ProjectLevelPlugins.androidLibrary) version Versions.androidLibrary apply Versions.applyFalse
    id(ProjectLevelPlugins.kotlinAndroid) version Versions.kotlinAndroid apply Versions.applyFalse
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}