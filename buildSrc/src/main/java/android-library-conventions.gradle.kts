plugins {
    // <2> apply them in plugins
    id("com.android.library") // should go first

    id("android-common-conventions")
    `maven-publish`
}

afterEvaluate {
    publishing {
        publications {

            register<MavenPublication>("release") {
                groupId = project.group.toString()
                artifactId = project.name // string-annotations
                version = project.version.toString()
                from(components["release"])
            }

        }
    }
}
