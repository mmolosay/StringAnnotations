plugins {
    id("android-library-conventions")
}

android {

    namespace = "${Project.GROUP_ID}.common.shared"

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
