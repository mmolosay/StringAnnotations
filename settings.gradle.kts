pluginManagement {
    plugins {
        // empty
    }
    resolutionStrategy {
        // empty
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "StringAnnotations"
//includeBuild(":buildSrc")
include(
    ":string-annotations"
)
