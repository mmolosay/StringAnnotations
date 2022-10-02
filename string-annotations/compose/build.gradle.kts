plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = "${Project.GROUP_ID}.compose"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))

    // Compose
}
