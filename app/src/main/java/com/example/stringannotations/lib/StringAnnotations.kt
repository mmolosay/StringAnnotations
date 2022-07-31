package com.example.stringannotations.lib

import com.example.stringannotations.processor.AnnotationProcessor

/**
 * Collection of library settings and required objects.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and never
 * going to need it again in terms of this process.
 */
object StringAnnotations {

    internal var annotationProcessor: AnnotationProcessor? = null

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