plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = Project.APPLICATION_ID

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
    implementation(project(":string-annotations"))

    // Compose
}
