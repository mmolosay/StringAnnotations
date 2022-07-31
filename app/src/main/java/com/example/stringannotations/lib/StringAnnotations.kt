package com.example.stringannotations.lib

import com.example.stringannotations.processor.AnnotationProcessor

/**
 * Collection of library settings and required dependincies.
 *
 * Call [StringAnnotations.configure] before any other interactions with library methods.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and want to
 * free internal dependencies.
 */
object StringAnnotations {

    internal var annotationProcessor: AnnotationProcessor? = null

    /**
     * Provides [Builder] instance to configure library dependencies.
     */
    fun configure(): Builder =
        BuilderImpl

    internal fun requireAnnotaitonProcessor(): AnnotationProcessor =
        annotationProcessor ?: throwUnconfiguredException()

    /**
     * Disposes all held objects.
     * Should be called, when [StringAnnotations] are never needed again.
     */
    fun dispose() {
        this.annotationProcessor = null
    }

    private fun throwUnconfiguredException(): Nothing =
        throw IllegalStateException(
            "StringAnnotations is not configured. " +
                "Make sure you have called StringAnnotations.configure() method."
        )

    sealed interface Builder {
        fun annotationProcessor(processor: AnnotationProcessor): Builder
    }
}