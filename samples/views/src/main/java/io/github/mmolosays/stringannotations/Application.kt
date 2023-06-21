package io.github.mmolosays.stringannotations

import android.app.Application
import io.github.mmolosays.stringannotations.processor.parser.DefaultValueParser
import io.github.mmolosays.stringannotations.views.StringAnnotations
import io.github.mmolosays.stringannotations.views.processor.MasterAnnotationProcessor

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
        val processor = MasterAnnotationProcessor(
            defaultValueParser = DefaultValueParser,
        )
        val dependencies = StringAnnotations.Dependencies(
            processor = processor,
        )
        StringAnnotations.configure(dependencies)
    }
}