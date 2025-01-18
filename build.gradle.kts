// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.xandroidx.navigation.navigation.safe.args.gradle.plugin2)
    }
}