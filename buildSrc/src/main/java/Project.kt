object Project {

    const val GROUP_ID = "com.mmolosay.stringannotations"
    const val VERSION = "1.6.0" // TODO: update

    private const val SAMPLE_NAMESPACE = "com.mmolosay.sample"
    private const val SAMPLE_APPLICATION_ID = "com.mmolosay.sample"

    // samples
    const val SAMPLE_VIEWS_NAMESPACE = "$SAMPLE_NAMESPACE.views"
    const val SAMPLE_VIEWS_ID = "$SAMPLE_APPLICATION_ID.views"

    const val SAMPLE_COMPOSE_NAMESPACE = "$SAMPLE_NAMESPACE.compose"
    const val SAMPLE_COMPOSE_ID = "$SAMPLE_APPLICATION_ID.compose"

    // artifacts
    const val ARTIFACT_VIEWS_ID = "views"
    const val ARTIFACT_VIEWS_NAMESPACE = "${GROUP_ID}.views"

    const val ARTIFACT_COMPOSE_ID = "compose"
    const val ARTIFACT_COMPOSE_NAMESPACE = "${GROUP_ID}.compose"

    // support
    const val SUPPORT_SERVICE_NAMESPACE = "${GROUP_ID}.service"
    const val SUPPORT_SHARED_NAMESPACE = GROUP_ID
    const val SUPPORT_INTERNAL_NAMESPACE = "${GROUP_ID}.internal"
}