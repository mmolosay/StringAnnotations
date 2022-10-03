plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = "${Project.GROUP_ID}.views"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    implementation("androidx.fragment:fragment-ktx:1.5.3") // for fragment extensions
}
