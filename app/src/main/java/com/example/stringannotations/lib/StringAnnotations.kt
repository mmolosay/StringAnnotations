package com.example.stringannotations.lib

import com.example.stringannotations.processor.AnnotationProcessor

/**
 * Collection of library settings and required dependincies.
 *
 * Call [StringAnnotations.configure] before any other interactions with library.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and ready to
 * free its dependencies.
 */
object StringAnnotations {

    internal var annotationProcessor: AnnotationProcessor? = null
    internal var clickableTextAppearance: ClickableTextAppearance? = null

    /**
     * Provides [Builder] instance to configure library dependencies.
     */
    fun configure(): Builder =
        BuilderImpl

    internal fun requireAnnotaitonProcessor(): AnnotationProcessor =
        annotationProcessor ?: throwUnconfiguredException()

    internal fun requireClickableTextAppearance(): ClickableTextAppearance =
        clickableTextAppearance ?: throwUnconfiguredException()

    /**
     * Disposes all held objects.
     * Should be called, when [StringAnnotations] is never needed again.
     */
    fun dispose() {
        this.annotationProcessor = null
        this.clickableTextAppearance = null
    }

    private fun throwUnconfiguredException(): Nothing =
        throw IllegalStateException(
            "StringAnnotations is not configured. " +
                "Make sure you have called StringAnnotations.configure() method."
        )

    /**
     * Defines dependencies of [StringAnnotations].
     * Make sure you have set all of them before working with library.
     */
    sealed interface Builder {
        /**
         * Defines [AnnotationProcessor] to be used.
         */
        fun annotationProcessor(processor: AnnotationProcessor): Builder

        /**
         * Defines [ClickableTextAppearance] to be used.
         */
        fun clickableTextAppearance(appearance: ClickableTextAppearance): Builder
    }
}