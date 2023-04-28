import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "io.github.mmolosays.stringannotations"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdk = 24
    }

    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {

    // Modules
    implementation(project(":string-annotations:common:service"))

    // Dependencies
    implementation("androidx.annotation:annotation:1.5.0") // @ColorInt and others
}
