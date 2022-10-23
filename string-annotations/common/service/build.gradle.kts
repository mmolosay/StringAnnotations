plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = Project.SUPPORT_SERVICE_NAMESPACE

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
                artifactId = Project.SUPPORT_SERVICE_ID
                version = Project.VERSION

                from(components["release"])
            }
        }
    }
}