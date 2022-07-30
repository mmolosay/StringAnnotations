package com.example.stringannotations.processors

import android.text.Annotation
import android.text.Spanned
import com.example.stringannotations.StringAnnotation

/**
 * Processes [StringAnnotation]s.
 */
internal object StringAnnotationProcessor {

    /**
     * Parses specified [annotations] of [string] into list of [StringAnnotation].
     */
    fun parseStringAnnotations(
        string: Spanned,
        annotations: Array<out Annotation>
    ): List<StringAnnotation> =
        annotations.mapIndexed { index, annotation ->
            val range = AnnotationProcessor.getAnnotationRange(string, annotation)
            if (range.first == -1 || range.last == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            StringAnnotation(annotation, range.first, range.last, index)
        }
}