package com.example.stringannotations.lib

import com.example.stringannotations.processor.AnnotationProcessor

internal object BuilderImpl : StringAnnotations.Builder {

    override fun annotationProcessor(processor: AnnotationProcessor): StringAnnotations.Builder =
        apply {
            StringAnnotations.annotationProcessor = processor
        }
}