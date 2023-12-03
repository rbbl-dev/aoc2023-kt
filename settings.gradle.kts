pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/amper/amper")
    }
}

plugins {
    id("org.jetbrains.amper.settings.plugin").version("0.1.1")
}

rootProject.name = "aoc2023"
include(":common")
include(":aio")
include(":day1")
include(":day2")
include(":day3")
