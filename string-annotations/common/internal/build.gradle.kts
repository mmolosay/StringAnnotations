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

    // Modules
    implementation(project(":string-annotations:common:shared"))
}
