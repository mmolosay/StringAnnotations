package com.mmolosay.sample

import android.app.Application
import com.mmolosay.stringannotations.StringAnnotations
import com.mmolosay.stringannotations.core.DefaultAnnotationProcessorResolver

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
        val resolver = DefaultAnnotationProcessorResolver()
        val dependencies = StringAnnotations.Dependencies.Builder()
            .annotationProcessorResolver(resolver)
            .build()
        StringAnnotations.configure(dependencies)
    }
}