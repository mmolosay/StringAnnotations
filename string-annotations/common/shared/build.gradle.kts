plugins {
    id("android-library-conventions")
}

android {

    namespace = Project.SUPPORT_SHARED_NAMESPACE

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
    implementation(project(":string-annotations:common:service"))

    // Dependencies
    implementation("androidx.annotation:annotation:1.5.0") // @ColorInt and others
}
