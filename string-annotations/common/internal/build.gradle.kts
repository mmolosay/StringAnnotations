plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations.internal"

    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

dependencies {

    // Modules
    implementation(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:service"))
}
