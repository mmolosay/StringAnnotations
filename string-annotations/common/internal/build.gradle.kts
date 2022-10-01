plugins {
    id("android-library-conventions")
}

android {

    namespace = "${Project.GROUP_ID}.internal"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    api("androidx.core:core-ktx:1.9.0")
}
