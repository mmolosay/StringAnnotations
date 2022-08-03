plugins {
    // <2> apply them in plugins
    id("com.android.library") // should go first

    id("android-common-conventions")
    `maven-publish`
}
