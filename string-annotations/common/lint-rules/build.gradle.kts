plugins {
    `java-library`
    kotlin
    id("com.android.lint")
}

kotlin {
    explicitApi()
}

lint {
    htmlReport = true
    htmlOutput = file("lint-report.html")
    textReport = true
    absolutePaths = false
    ignoreTestSources = true
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")
    compileOnly("com.android.tools.lint:lint-api:30.3.0")

//    compileOnly("com.android.tools.lint:lint-checks:$lintVersion")

//    testImplementation("com.android.tools.lint:lint:$lintVersion")
//    testImplementation("com.android.tools.lint:lint-tests:$lintVersion")
}