plugins {
    id("android-library-conventions")
}

android {

    namespace = Project.GROUP_ID

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

    implementation(project(":string-annotations:common:service"))
    // Modules

    // Dependencies
    implementation("androidx.annotation:annotation:1.5.0") // @ColorInt and others
}