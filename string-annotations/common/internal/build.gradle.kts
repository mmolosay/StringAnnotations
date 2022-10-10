plugins {
    id("android-library-conventions")
}

android {

    namespace = Project.SUPPORT_INTERNAL_NAMESPACE

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
    implementation(project(":string-annotations:common:service"))
}
