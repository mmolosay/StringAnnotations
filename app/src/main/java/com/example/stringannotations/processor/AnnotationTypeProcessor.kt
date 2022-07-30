package com.example.stringannotations.processor

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Annotation
import android.util.Log
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

    private fun parseAnnotationType(
        context: Context,
        annotation: Annotation
    ): AnnotationType {
        val values = parseAnnotationValue(annotation.value)
        return when (annotation.key) {
            ANNOTATION_KEY_BACKGROUND -> parseBackgrounAnnotation(context, values)
            ANNOTATION_KEY_FOREGROUND -> parseForegroundAnnotation(context, values)
            ANNOTATION_KEY_TYPEFACE_STYLE -> parseTypefaceStyleAnnotation(values)
            else -> AnnotationType.Unknown
        }
    }

    /**
     * Splits annotation value of type `value1[|value2|value3|...]` into list of
     * separate atomic values and reduces repeated entries.
     */
    private fun parseAnnotationValue(value: String): List<String> =
        value
            .split(ANNOTATION_VALUE_COMBINE_SYMBOL)
            .distinct()

    private fun parseBackgrounAnnotation(
        context: Context,
        values: List<String>
    ): AnnotationType {
        val value = AnnotationValuesPickingStrategy.Single(values)
        val color = parseColorAttributeValue(context, value)
        return AnnotationType.Background(color)
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<String>
    ): AnnotationType {
        val value = AnnotationValuesPickingStrategy.Single(values)
        val color = parseColorAttributeValue(context, value)
        return AnnotationType.Foreground(color)
    }

    private fun parseTypefaceStyleAnnotation(
        values: List<String>
    ): AnnotationType {
        val styles = values
            .map { value ->
                when (value) {
                    ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD -> Typeface.BOLD
                    ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC -> Typeface.ITALIC
                    else -> Typeface.NORMAL
                }
            }
            .distinct() // drop possibly duplicated normals due to multiple invalid values
        val style = reduceTypefaceStyles(styles)
        return AnnotationType.TypefaceStyle(style)
    }

    /**
     * Parses string [value] of any color attribute (like [ANNOTATION_KEY_FOREGROUND] or
     * [ANNOTATION_KEY_BACKGROUND]) into color integer.
     *
     * Supported types of attribute value:
     * 1. color hex: `#ff0000`
     * 2. color name: `green`
     * 3. color resource name: `myColorRes`
     *
     * If valid color can not be parsed, then [Color.BLACK] will be used as a fallback one.
     * Relevant message would be logged with [Log.WARN] priority.
     */
    @ColorInt
    private fun parseColorAttributeValue(
        context: Context,
        value: String
    ): Int =
        try {
            Color.parseColor(value)
        } catch (e: IllegalArgumentException) {
            try {
                val packageName = context.packageName
                val colorRes = context.resources.getIdentifier(value, "color", packageName)
                ContextCompat.getColor(context, colorRes)
            } catch (e: Resources.NotFoundException) {
                Log.w(
                    this::class.simpleName,
                    "string annotation with attribute value=\"$value\" can not be parsed into valid color"
                )
                Color.BLACK // return fallback color, if attribute value is invalid
            }
        }

    private fun reduceTypefaceStyles(styles: List<Int>): Int {
        if (styles.size == 1) return styles.first()
        return if (styles.contains(Typeface.NORMAL)) {
            reduceTypefaceStyles(styles - Typeface.NORMAL)
        } else {
            Typeface.BOLD_ITALIC
        }
    }

    private object AnnotationValuesPickingStrategy {

        fun Single(values: List<String>): String =
            values.first()

        fun AllCoexisting(
            values: List<String>,
            filter: (initial: List<String>) -> List<String>
        ): List<String> =
            filter(values)
    }

    private const val ANNOTATION_KEY_BACKGROUND = "background"
    private const val ANNOTATION_KEY_FOREGROUND = "color"
    private const val ANNOTATION_KEY_TYPEFACE_STYLE = "style"

    private const val ANNOTATION_VALUE_TYPEFACE_STYLE_NORMAL = "normal"
    private const val ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD = "bold"
    private const val ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC = "italic"

    private const val ANNOTATION_VALUE_COMBINE_SYMBOL = "|"
}