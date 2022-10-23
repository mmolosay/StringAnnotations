plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = Project.SUPPORT_INTERNAL_NAMESPACE

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
                artifactId = Project.SUPPORT_INTERNAL_ID
                version = Project.VERSION

                from(components["release"])
            }
        }
    }
}

dependencies {

    // Modules
    implementation(Project.SUPPORT_SHARED_DEPENDENCY)
    implementation(Project.SUPPORT_SERVICE_DEPENDENCY)
}
