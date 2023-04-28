import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations.sample"

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

    defaultConfig {
        applicationId = "io.github.mmolosays.stringannotations.sample.compose"
        minSdk = 24
        targetSdk = 33
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
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