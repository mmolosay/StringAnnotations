package com.example.stringannotations.processors

import android.content.Context
import android.graphics.Color
import android.text.Annotation
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.stringannotations.AnnotationType

/**
 * Processes [AnnotationType]s.
 */
internal object AnnotationTypeProcessor {

    /**
     * Parses [AnnotationType]s from [annotations] of some spanned string.
     */
    fun parseAnnotationTypes(
        context: Context,
        annotations: Array<out Annotation>
    ): List<AnnotationType> =
        annotations.map { span -> parseAnnotationType(context, span) }


    fun parseAnnotationType(
        context: Context,
        annotation: Annotation
    ): AnnotationType =
        when (annotation.key) {
            ANNOTATION_KEY_BACKGROUND -> parseBackgrounAnnotation(annotation.value)
            ANNOTATION_KEY_COLOR_HEX -> parseColorHexAnnotation(annotation.value)
            ANNOTATION_KEY_COLOR_RES -> parseColorResAnnotation(context, annotation.value)
            else -> AnnotationType.Unknown
        }

    private fun parseColorHexAnnotation(color: String): AnnotationType.Color =
        AnnotationType.Color(
            color = Color.parseColor(color)
        )

    private fun parseColorResAnnotation(
        context: Context,
        colorResName: String
    ): AnnotationType.Color {
        val packageName = context.packageName
        val colorRes = context.resources.getIdentifier(colorResName, "color", packageName)
        return AnnotationType.Color(
            color = ContextCompat.getColor(context, colorRes)
        )
    }

    private fun parseBackgrounAnnotation(color: String): AnnotationType.Background =
        AnnotationType.Background(
            color = Color.parseColor(color)
        )

    private const val ANNOTATION_KEY_COLOR_HEX = "color"
    private const val ANNOTATION_KEY_COLOR_RES = "color-res"
    private const val ANNOTATION_KEY_BACKGROUND = "background"
}