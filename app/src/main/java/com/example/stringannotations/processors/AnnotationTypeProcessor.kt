package com.example.stringannotations.processors

import android.graphics.Color
import android.text.Annotation
import com.example.stringannotations.AnnotationType

/**
 * Processes [AnnotationType]s.
 */
internal object AnnotationTypeProcessor {

    /**
     * Parses [AnnotationType]s from [annotations] of specified [string].
     */
    fun parseAnnotationTypes(
        spans: Array<out Annotation>
    ): List<AnnotationType> =
        spans.map { span -> parseAnnotationType(span) }

    fun parseAnnotationType(annotation: Annotation): AnnotationType =
        when (annotation.key) {
            ANNOTATION_KEY_COLOR_HEX -> parseColorHexAnnotation(annotation.value)
            ANNOTATION_KEY_BACKGROUND
        }

    fun parseColorHexAnnotation(value: String): AnnotationType.ColorHex =
        AnnotationType.ColorHex(
            color = Color.parseColor(value)
        )

    fun parseColorResAnnotation(value: String): AnnotationType.ColorHex =
        AnnotationType.ColorHex(
            color = Color.parseColor(value)
        )

    private const val ANNOTATION_KEY_COLOR_HEX = "color"
    private const val ANNOTATION_KEY_COLOR_RES = "color-res"
    private const val ANNOTATION_KEY_BACKGROUND = "background"
}