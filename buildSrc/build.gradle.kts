plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    // <1> define dependencies here
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10") // koltin
    implementation("com.android.tools.build:gradle:7.3.0") // com.android.library and stuff
}
