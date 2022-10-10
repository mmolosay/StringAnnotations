object Project {

    const val GROUP_ID = "com.mmolosay.stringannotations"
    const val ARTIFACT_ID = "stringannotations" // repo name
    const val VERSION = "1.6.0" // TODO: update

    private const val APPLICATION_ID = "com.mmolosay.sample"

    // samples
    const val SAMPLE_VIEWS_ID = "$APPLICATION_ID.views"
    const val SAMPLE_COMPOSE_ID = "$APPLICATION_ID.compose"

    // artifacts
    const val ARTIFACT_VIEWS_NAMESPACE = "${GROUP_ID}.views"
    const val ARTIFACT_COMPOSE_NAMESPACE = "${GROUP_ID}.compose"

    // support
    const val SUPPORT_SERVICE_NAMESPACE = "${GROUP_ID}.service"
    const val SUPPORT_SHARED_NAMESPACE = GROUP_ID
    const val SUPPORT_INTERNAL_NAMESPACE = "${GROUP_ID}.internal"
}