plugins {
    id("com.android.application") // should go first
    id("android-common-conventions")
}

android {
    defaultConfig {
        applicationId = "com.mmolosay.stringannotations.sample"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}