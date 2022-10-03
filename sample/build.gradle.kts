plugins {
    id("android-application-conventions")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":string-annotations:views"))
    // since it's a sample project, it uses library as module
    // you should use it as a external dependency, see Installation section of library's README.md

    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
}