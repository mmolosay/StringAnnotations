package com.mmolosay.sample

import android.app.Application
import com.mmolosay.stringannotations.core.StringAnnotations
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.DefaultAnnotationProcessor

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setStringAnnotations()
    }

    /**
     * Configures `StringAnnotations` library.
     */
    private fun setStringAnnotations() {
        val processor = makeAnnotationProcessor()
        StringAnnotations.configure()
            .annotationProcessor(processor)
    }

    /**
     * Creates new instance of [AnnotationProcessor].
     */
    private fun makeAnnotationProcessor(): AnnotationProcessor =
        DefaultAnnotationProcessor()
}