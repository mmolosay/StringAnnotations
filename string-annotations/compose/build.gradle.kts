plugins {
    id("android-artifact-library-conventions")
}

android {

    namespace = Project.ARTIFACT_COMPOSE_NAMESPACE

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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = Project.GROUP_ID
                artifactId = Project.ARTIFACT_COMPOSE_ID
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

    // Compose
    implementation("androidx.compose.ui:ui:1.3.0")
}
