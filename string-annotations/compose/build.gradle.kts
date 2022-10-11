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
        kotlinCompilerExtensionVersion = "1.3.1"
    }
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = Project.GROUP_ID
                artifactId = Project.ARTIFACT_COMPOSE_ID
                version = Project.VERSION
            }
        }
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    // Compose
    implementation("androidx.compose.ui:ui:1.2.1")
//    implementation("androidx.compose.ui:ui-tooling:1.2.1")
//    implementation("androidx.compose.foundation:foundation:1.2.1")
}
