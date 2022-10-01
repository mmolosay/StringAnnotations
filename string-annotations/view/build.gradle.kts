plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = "${Project.GROUP_ID}.view"

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

    // TODO: try find dependency with just 'Fragment'
    implementation("androidx.appcompat:appcompat:1.4.2") // for fragment extensions
}
