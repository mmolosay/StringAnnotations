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

// Sub-modules
include(":string-annotations:common:internal")
include(":string-annotations:common:shared")
include(":string-annotations:common:utils")

// Artifacts
include(":string-annotations:views")
include(":string-annotations:compose")

// Samples
include(":samples:views")
include(":samples:compose")
