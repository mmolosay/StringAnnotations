package com.mmolosay.stringannotations.core

import com.mmolosay.stringannotations.processor.AnnotationProcessor

internal object BuilderImpl : StringAnnotations.Builder {

    override fun annotationProcessor(processor: AnnotationProcessor): StringAnnotations.Builder =
        apply {
            StringAnnotations.annotationProcessor = processor
        }
}