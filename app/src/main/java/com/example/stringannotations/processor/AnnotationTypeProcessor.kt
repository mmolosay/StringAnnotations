package com.example.stringannotations.processor

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Annotation
import androidx.annotation.ColorInt
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
            ANNOTATION_KEY_BACKGROUND -> parseBackgrounAnnotation(context, annotation.value)
            ANNOTATION_KEY_FOREGROUND -> parseForegroundAnnotation(context, annotation.value)
            ANNOTATION_KEY_TYPEFACE_STYLE -> parseTypefaceStyleAnnotation(annotation.value)
            else -> AnnotationType.Unknown
        }

    private fun parseBackgrounAnnotation(
        context: Context,
        color: String
    ) =
        AnnotationType.Background(
            color = parseColorAttributeValue(context, color)
        )

    private fun parseForegroundAnnotation(
        context: Context,
        color: String
    ) =
        AnnotationType.Foreground(
            color = parseColorAttributeValue(context, color)
        )

    private fun parseTypefaceStyleAnnotation(
        style: String
    ) =
        AnnotationType.TypefaceStyle(
            style = inferTypefaceStyle(style)
        )

    /**
     * Parses string value of any color attribute (like [ANNOTATION_KEY_FOREGROUND] or
     * [ANNOTATION_KEY_BACKGROUND]) into color integer.
     *
     * Supported types of attribute value:
     * 1. color hex: `#ff0000`
     * 2. color name: `green`
     * 3. color resource name: `myColorRes`
     */
    @ColorInt
    private fun parseColorAttributeValue(
        context: Context,
        color: String
    ): Int =
        try {
            Color.parseColor(color)
        } catch (e: IllegalArgumentException) {
            try {
                val packageName = context.packageName
                val colorRes = context.resources.getIdentifier(color, "color", packageName)
                ContextCompat.getColor(context, colorRes)
            } catch (e: Resources.NotFoundException) {
                // TODO: log warning
                Color.BLACK // return fallback color, if attribute value is invalid
            }
        }

    private fun inferTypefaceStyle(style: String): Int =
        when (style) {
            ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD -> Typeface.BOLD
            ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC -> Typeface.ITALIC
            else -> Typeface.NORMAL // for normal and invalid
        }

    private const val ANNOTATION_KEY_BACKGROUND = "background"
    private const val ANNOTATION_KEY_FOREGROUND = "color"
    private const val ANNOTATION_KEY_TYPEFACE_STYLE = "style"

    private const val ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD = "bold"
    private const val ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC = "italic"
    // TODO: add other
}