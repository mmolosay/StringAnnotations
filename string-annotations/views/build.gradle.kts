plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = Project.ARTIFACT_VIEWS_NAMESPACE

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

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = Project.GROUP_ID
                artifactId = Project.ARTIFACT_VIEWS_ID
                version = Project.VERSION

                from(components["release"])
            }
        }
    }
}

dependencies {

    // Modules
    api(Project.SUPPORT_SHARED_DEPENDENCY)
    implementation(Project.SUPPORT_INTERNAL_DEPENDENCY)

    implementation("androidx.fragment:fragment-ktx:1.5.4") // for fragment extensions
}
