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
include(
    ":string-annotations:common:service",
    ":string-annotations:common:shared",
    ":string-annotations:common:internal",
    ":string-annotations:views",
    ":string-annotations:compose",
    ":sample-views",
    ":sample-compose"
)
