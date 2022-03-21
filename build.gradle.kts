// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.androidHilt}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationUIKtx}")
    }
}

plugins {
    id(ProjectPlugins.androidApplication) version Versions.androidApplication apply Versions.applyFalse
    id(ProjectPlugins.androidLibrary) version Versions.androidLibrary apply Versions.applyFalse
    id(ProjectPlugins.kotlinAndroid) version Versions.kotlinAndroid apply Versions.applyFalse
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}