plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations.service"

    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}