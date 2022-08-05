plugins {
    // <2> apply them in plugins
    id("com.android.library") // should go first

    id("android-common-conventions")
    `maven-publish`
}

android {
    kotlinOptions {
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

afterEvaluate {
    publishing {
        publications {

            register<MavenPublication>("release") {
                from(components["release"])
                groupId = Project.GROUP_ID
                artifactId = Project.ARTIFACT_ID
                version = Project.VERSION
            }

        }
    }
}
