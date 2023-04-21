plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations"

    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

dependencies {

    // Modules
    implementation(project(":string-annotations:common:service"))

    // Dependencies
    implementation("androidx.annotation:annotation:1.5.0") // @ColorInt and others
}
