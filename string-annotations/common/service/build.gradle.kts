plugins {
    id("android-library-conventions")
}

android {

    namespace = Project.SUPPORT_SERVICE_NAMESPACE

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

    // Dependencies
    implementation("androidx.annotation:annotation:1.5.0") // @ColorInt and others
}
