package com.mmolosay.sample

import android.app.Application
import com.mmolosay.stringannotations.StringAnnotations
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.DefaultAnnotationProcessor

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setStringAnnotations()
    }

    /**
     * Configures `StringAnnotations` library.
     *
     * Call to [StringAnnotations.configure] method in not necessary, since the library
     * will be configured with default dependencies, if there was no custom ones specified.
     */
    private fun setStringAnnotations() {
        val processor = makeAnnotationProcessor()
        StringAnnotations.configure(
            annotationProcessor = processor
        )
    }

    /**
     * Creates new instance of [AnnotationProcessor].
     */
    private fun makeAnnotationProcessor(): AnnotationProcessor =
        DefaultAnnotationProcessor()
}