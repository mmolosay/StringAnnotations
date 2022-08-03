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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10") // koltin
    implementation("com.android.tools.build:gradle:7.2.1") // com.android.library and stuff
}
