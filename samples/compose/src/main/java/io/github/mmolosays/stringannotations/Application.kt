package io.github.mmolosays.stringannotations

import android.app.Application
import io.github.mmolosays.stringannotations.compose.StringAnnotations
import io.github.mmolosays.stringannotations.custom.CustomMasterAnnotationProcessor

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
        val dependencies = StringAnnotations.Dependencies(
            processor = processor,
        )
        StringAnnotations.configure(dependencies)
    }
}