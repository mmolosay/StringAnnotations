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
    ":string-annotations:common:shared",
    ":string-annotations:common:internal",
    ":string-annotations:common:service",
    ":string-annotations:view",
    ":string-annotations:compose",
    ":sample"
)
