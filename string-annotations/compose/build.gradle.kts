plugins {
    id("android-library-conventions")
}

android {
    namespace = Project.ARTIFACT_COMPOSE_NAMESPACE

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    // Compose
    implementation("androidx.compose.ui:ui:1.3.0")
}
