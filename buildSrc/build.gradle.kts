plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven("https://jitpack.io")
}

dependencies {
    // <1> define dependencies here using Maven coordinates, not plugin ID
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20") // koltin
    implementation("com.android.tools.build:gradle:8.0.0") // com.android.library and stuff
}
