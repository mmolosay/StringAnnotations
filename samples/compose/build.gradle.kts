plugins {
    id("android-application-conventions")
}

android {
    namespace = Project.SAMPLE_COMPOSE_NAMESPACE

    defaultConfig {
        applicationId = Project.SAMPLE_COMPOSE_ID
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {

    implementation(project(":string-annotations:compose"))
    // since it's a sample project, it uses library as module
    // you should use it as a external dependency, see Installation section of project's README.md

    // AndroidX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")

    // Design
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.compose.material3:material3:1.0.0-beta03")

    // Compose
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.activity:activity-compose:1.6.0")
}