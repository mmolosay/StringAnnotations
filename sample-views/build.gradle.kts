plugins {
    id("android-application-conventions")
}

android {
    defaultConfig {
        applicationId = Project.VIEWS_SAMPLE_ID
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":string-annotations:views"))
    // since it's a sample project, it uses library as module
    // you should use it as a external dependency, see Installation section of project's README.md

    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
}