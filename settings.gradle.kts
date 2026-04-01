pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven("https://packages.jetbrains.team/maven/p/kt/bootstrap")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://packages.jetbrains.team/maven/p/kt/bootstrap")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "power-assert-examples"

include(":fluent-assert")
include(":kotlin-test-power-assert")
include(":pretty-print")
