package com.mmolosay.sample

import android.app.Application
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.views.StringAnnotations
import com.mmolosay.stringannotations.views.processor.MasterAnnotationProcessor

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        configureStringAnnotations()
    }

    /**
     * Configures `StringAnnotations` library.
     *
     * Call to [StringAnnotations.configure] method in not necessary, since the library
     * will be configured with default dependencies, if there was no custom ones specified.
     */
    private fun configureStringAnnotations() {
        val processor: AnnotationProcessor<CharacterStyle> = MasterAnnotationProcessor()
        val dependencies = StringAnnotations.DependenciesBuilder()
            .annotationProcessor(processor)
            .build()
        StringAnnotations.configure(dependencies)
    }
}