plugins {
    id("android-library-conventions")
}

android {
    namespace = Project.ARTIFACT_VIEWS_NAMESPACE
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    implementation("androidx.fragment:fragment-ktx:1.5.4") // for fragment extensions
}
