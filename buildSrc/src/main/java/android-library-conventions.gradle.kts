plugins {
    // <2> apply them in plugins
    id("com.android.library") // should go first
    id("android-common-conventions")
}

android {
    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}