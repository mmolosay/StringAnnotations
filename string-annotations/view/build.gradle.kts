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

    // TODO: try find dependency with just 'Fragment'
    implementation("androidx.appcompat:appcompat:1.4.2") // for fragment extensions
}
