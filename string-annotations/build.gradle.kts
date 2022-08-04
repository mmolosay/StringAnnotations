plugins {
    id("android-library-conventions")
}

android {

    namespace = project.group.toString()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    implementation("androidx.appcompat:appcompat:1.4.2") // for fragment extensions
}
