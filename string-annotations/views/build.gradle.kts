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
                from(components["release"])
                groupId = Project.GROUP_ID
                artifactId = Project.ARTIFACT_VIEWS_ID
                version = Project.VERSION
            }
        }
    }
}

dependencies {

    // Modules
    api(project(":string-annotations:common:shared"))
    implementation(project(":string-annotations:common:internal"))

    implementation("androidx.fragment:fragment-ktx:1.5.3") // for fragment extensions
}
