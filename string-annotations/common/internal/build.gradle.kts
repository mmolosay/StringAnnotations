plugins {
    id("android-library-conventions")
}

android {
    namespace = Project.SUPPORT_INTERNAL_NAMESPACE
}

dependencies {

    // Modules
    implementation(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:service"))
}
