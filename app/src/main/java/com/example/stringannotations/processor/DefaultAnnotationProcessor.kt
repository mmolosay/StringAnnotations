package com.example.stringannotations.processor

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Annotation
import android.text.style.ClickableSpan
import android.util.Log
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.stringannotations.AnnotationType

/**
 * Default implementation of [AnnotationProcessor].
 * It is able to process all default types of [AnnotationType].
 *
 * One should inherit this class in order to process custom [AnnotationType].
 */
open class DefaultAnnotationProcessor : AnnotationProcessor {

    /**
     * Symbol, that is used in tag value to combine multiple values.
     */
    protected val ANNOTATION_VALUE_COMBINE_SYMBOL = "|"

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        clickables: List<ClickableSpan>
    ): AnnotationType {
        val values = parseAnnotationValue(annotation.value)
        return parseAnnotation(context, annotation.key, values, clickables)
    }

    /**
     * Implementation of [parseAnnotation] with provided [Annotation]'s key and values.
     *
     * Derived class should override this method and call super's implementation at the beginning
     * in order to parse custom [AnnotationType].
     *
     * @param context caller context.
     * @param key [Annotation.getKey], which is actually a tag's attribute name.
     * @param values list of split tag's attribute values (see [parseAnnotationValue]).
     * @param clickables list of [ClickableSpan], that will be used for [AnnotationType.Clickable] types.
     */
    open fun parseAnnotation(
        context: Context,
        key: String,
        values: List<String>,
        clickables: List<ClickableSpan>
    ): AnnotationType =
        when (key) {
            ANNOTATION_KEY_BACKGROUND -> parseBackgrounAnnotation(context, values)
            ANNOTATION_KEY_FOREGROUND -> parseForegroundAnnotation(context, values)
            ANNOTATION_KEY_STYLE -> parseStyleAnnotation(values)
            ANNOTATION_KEY_CLICKABLE -> parseClickableAnnotation(values, clickables)
            else -> AnnotationType.Null
        }

    /**
     * Splits annotation value of type `value1[|value2|value3|...]` into list of
     * separate atomic values and reduces repeated entries.
     */
    open fun parseAnnotationValue(value: String): List<String> =
        value
            .split(ANNOTATION_VALUE_COMBINE_SYMBOL)
            .distinct()

    private fun parseBackgrounAnnotation(
        context: Context,
        values: List<String>
    ): AnnotationType {
        // TODO: log warning here and in all other parsers, if there was invalid value.
        val value = values.first()
        val color = parseColorAttributeValue(context, value)
        return AnnotationType.Background(color)
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<String>
    ): AnnotationType {
        val value = values.first()
        val color = parseColorAttributeValue(context, value)
        return AnnotationType.Foreground(color)
    }

    private fun parseStyleAnnotation(
        values: List<String>
    ): AnnotationType =
        when {
            values.contains(ANNOTATION_VALUE_UNDERLINE) -> AnnotationType.UnderlineStyle
            values.contains(ANNOTATION_VALUE_STRIKETHROUGH) -> AnnotationType.StrikethroughStyle
            else -> parseTypefaceStyleAnnotation(values)
        }

    private fun parseTypefaceStyleAnnotation(
        values: List<String>
    ): AnnotationType {
        val styles = values.mapNotNull { value ->
            inferTypefaceStyle(value)
        }
        val style = reduceTypefaceStyles(styles)
        return AnnotationType.TypefaceStyle(style)
    }

    private fun parseClickableAnnotation(
        values: List<String>,
        clickables: List<ClickableSpan>
    ): AnnotationType {
        val index = values.first().toIntOrNull() ?: 0
        val span = clickables.getOrNull(index) ?: return AnnotationType.Null
        return AnnotationType.Clickable(span)
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

    /**
     * Infer typeface style from annotation [value].
     *
     * @return typeface style or `null`, if there's no matches.
     */
    private fun inferTypefaceStyle(value: String): Int? =
        when (value) {
            ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD -> Typeface.BOLD
            ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC -> Typeface.ITALIC
            ANNOTATION_VALUE_TYPEFACE_STYLE_NORMAL -> Typeface.NORMAL
            else -> null // gibberish
        }

    /**
     * Reduces specified typeface [styles] into single one.
     * All [Typeface.NORMAL] would be reduced, if there any other ones.
     *
     * ```
     * Samples:
     * [bold] -> bold
     * [italic, normal] -> italic
     * [normal, bold, italic] -> bold_italic
     * ```
     */
    private fun reduceTypefaceStyles(styles: List<Int>): Int {
        if (styles.size == 1) return styles.first()
        return if (styles.contains(Typeface.NORMAL)) {
            reduceTypefaceStyles(styles - Typeface.NORMAL)
        } else {
            Typeface.BOLD_ITALIC
        }
    }

    companion object {

        // keys
        private const val ANNOTATION_KEY_BACKGROUND = "background"
        private const val ANNOTATION_KEY_FOREGROUND = "color"
        private const val ANNOTATION_KEY_STYLE = "style"
        private const val ANNOTATION_KEY_CLICKABLE = "clickable"

        // values
        private const val ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD = "bold"
        private const val ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC = "italic"
        private const val ANNOTATION_VALUE_TYPEFACE_STYLE_NORMAL = "normal"

        private const val ANNOTATION_VALUE_UNDERLINE = "underline"
        private const val ANNOTATION_VALUE_STRIKETHROUGH = "strike"
    }
}