package com.mmolosay.sample.compose

import android.app.Application
import com.mmolosay.sample.compose.custom.CustomMasterAnnotationProcessor
import com.mmolosay.stringannotations.compose.StringAnnotations

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
        val processor = CustomMasterAnnotationProcessor()
        val dependencies = StringAnnotations.DependenciesBuilder()
            .annotationProcessor(processor)
            .build()
        StringAnnotations.configure(dependencies)
    }
}