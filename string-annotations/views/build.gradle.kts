plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations.views"

    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    implementation("androidx.fragment:fragment-ktx:1.5.4") // for fragment extensions
}
