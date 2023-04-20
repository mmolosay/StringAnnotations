plugins {
    id("com.android.application") // should go first
    id("android-common-conventions")
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}