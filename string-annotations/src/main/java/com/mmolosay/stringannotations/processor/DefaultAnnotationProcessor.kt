package com.mmolosay.stringannotations.processor

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Annotation
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

/**
 * Default implementation of [AnnotationProcessor].
 * It is able to process all default annotation types.
 *
 * One should inherit this class in order to process custom annotation type.
 *
 * ## List of default supported annotations:
 *
 * ### Background color
 *
 * Annotation, that specifies background color of its body.
 *
 * ```
 * HEX color:
 * <annotation background="#ff0000">text with red background</annotation>
 *
 * Generic color name:
 * <annotation background="green">text with green background</annotation>
 *
 * Color resource name:
 * <annotation background="yourColorResName">text with colored background</annotation>
 * ```
 *
 * ### Foreground color
 *
 * Annotation, that specifies color of its body.
 *
 * ```
 * HEX color:
 * <annotation color="#ff0000">red text</annotation>
 *
 * Generic color name:
 * <annotation color="green">green text</annotation>
 *
 * Color resource name:
 * <annotation color="yourColorResName">colored text</annotation>
 * ```
 *
 * ### Clickable
 *
 * Annotation, that specifies click action for its body.
 *
 * Value of attribute is an index, at which corresponding [ClickableSpan]
 * is located in list you should provide.
 *
 * You should also explicitly specify, that your `TextView` contains clickable text
 * by calling [android.widget.TextView.setMovementMethod].
 *
 * ```
 * <annotation clickable="0">clickable text</annotation>
 * ```
 *
 * ### Typeface style
 *
 * Annotation, that specifies typeface style for its body.
 *
 * Value of attribute may be combination of "normal", 'bold" and "italic" styles.
 *
 * ```
 * <annotation style="bold|italic">bold and italic text</annotaiton>
 * ```
 *
 * ### Strikethrough style
 *
 * Annotation, that crosses its body out.
 *
 * ```
 * <annotation style="strikethrough">crossed out text</annotation>
 * ```
 *
 * ### Underline style
 *
 * Annotation, that underlines its body.
 *
 * ```
 * <annotation style="underline">underlined text</annotation>
 * ```
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
    ): CharacterStyle? {
        val values = parseAnnotationValue(annotation.value)
        return parseAnnotation(context, annotation.key, values, clickables)
    }

    /**
     * Implementation of [parseAnnotation] with provided [Annotation]'s key and values.
     *
     * Derived class should override this method and call super's implementation at the beginning
     * in order to parse custom annotation type.
     *
     * @param context caller context.
     * @param key [Annotation.getKey], which is actually a tag's attribute name.
     * @param values list of split tag's attribute values (see [parseAnnotationValue]).
     * @param clickables list of [ClickableSpan], that will be used for clickable spans.
     */
    open fun parseAnnotation(
        context: Context,
        key: String,
        values: List<String>,
        clickables: List<ClickableSpan>
    ): CharacterStyle? =
        when (key) {
            ANNOTATION_KEY_BACKGROUND -> parseBackgrounAnnotation(context, values)
            ANNOTATION_KEY_FOREGROUND -> parseForegroundAnnotation(context, values)
            ANNOTATION_KEY_STYLE -> parseStyleAnnotation(values)
            ANNOTATION_KEY_CLICKABLE -> parseClickableAnnotation(values, clickables)
            else -> null
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
    ): CharacterStyle? {
        val value = values.first() // use very first one
        return parseColorAttributeValue(context, value)?.let { color ->
            BackgroundColorSpan(color)
        }
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<String>
    ): CharacterStyle? {
        val value = values.first() // use very first one
        return parseColorAttributeValue(context, value)?.let { color ->
            ForegroundColorSpan(color)
        }
    }

    private fun parseStyleAnnotation(
        values: List<String>
    ): CharacterStyle? =
        when {
            values.contains(ANNOTATION_VALUE_UNDERLINE) -> UnderlineSpan()
            values.contains(ANNOTATION_VALUE_STRIKETHROUGH) -> StrikethroughSpan()
            else -> parseTypefaceStyleAnnotation(values)
        }

    private fun parseTypefaceStyleAnnotation(
        values: List<String>
    ): CharacterStyle? {
        val styles = values.mapNotNull { value ->
            inferTypefaceStyle(value)
        }
        return reduceTypefaceStyles(styles)?.let { style ->
            StyleSpan(style)
        }
    }

    private fun parseClickableAnnotation(
        values: List<String>,
        clickables: List<ClickableSpan>
    ): CharacterStyle? {
        val index = values.first().toIntOrNull() ?: return null
        return clickables.getOrNull(index)
    }

    // TODO: parse color attribute as well
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
    ): Int? =
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
                null // return null, if attribute value is invalid
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
    private fun reduceTypefaceStyles(styles: List<Int>): Int? {
        if (styles.isEmpty()) return null
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
        private const val ANNOTATION_VALUE_STRIKETHROUGH = "strikethrough"
    }
}