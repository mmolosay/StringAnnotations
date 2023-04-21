import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations.sample"

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        applicationId = "io.github.mmolosays.stringannotations.sample.views"
        minSdk = 24
        targetSdk = 33
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":string-annotations:views"))
    // since it's a sample project, it uses library as module
    // you should use it as a external dependency, see Installation section of project's README.md

    // AndroidX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")

    // Desing
    implementation("com.google.android.material:material:1.6.1")

    // Third-party libraries
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")
}