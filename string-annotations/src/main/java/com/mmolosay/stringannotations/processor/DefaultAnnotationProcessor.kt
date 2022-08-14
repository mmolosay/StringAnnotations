package com.mmolosay.stringannotations.processor

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Annotation
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import com.mmolosay.stringannotations.core.StringAnnotations

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * Annotation, that specifies ability of its body to intercept click events.
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
 * Annotation, that specifies typeface style of its body.
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
 *
 * ### Absolute size
 *
 * Annotation, that specifies absolute size of its body.
 *
 * ```
 * Pixels (just size will be treated the same):
 * <annotation size-absolute="20.3px">text of 20.3 px size</annotation>
 *
 * DPs:
 * <annotation size-absolute="20.3dp">text of 20.3 DP size</annotation>
 *
 * SPs:
 * <annotation size-absolute="20.3sp">text of 20.3 SP size</annotation>
 * ```
 */
public open class DefaultAnnotationProcessor : AnnotationProcessor {

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        clickables: List<ClickableSpan>,
        valueArgs: Array<out Any>
    ): CharacterStyle? {
        val type = annotation.key
        val value = annotation.value
        val values = parseAnnotationValue(value)
        return parseAnnotation(context, type, values, clickables, valueArgs).also { span ->
            span ?: logAnnotationParsingWarning(type, value)
        }
    }

    /**
     * Implementation of [parseAnnotation] with provided [Annotation]'s key and values.
     *
     * Derived class should override this method and call super's implementation at the beginning
     * in order to parse custom annotation type.
     *
     * @param context caller context.
     * @param type [Annotation.getKey], which is actually a tag's attribute name.
     * @param values list of split tag's attribute values (see [parseAnnotationValue]).
     * @param clickables list of [ClickableSpan], that will be used for clickable spans.
     */
    internal open fun parseAnnotation(
        context: Context,
        type: String,
        values: List<String>,
        clickables: List<ClickableSpan>,
        valueArgs: Array<out Any>
    ): CharacterStyle? =
        when (type) {
            ANNOTATION_TYPE_BACKGROUND -> parseBackgrounAnnotation(context, values, valueArgs)
            ANNOTATION_TYPE_FOREGROUND -> parseForegroundAnnotation(context, values)
            ANNOTATION_TYPE_STYLE -> parseStyleAnnotation(values)
            ANNOTATION_TYPE_CLICKABLE -> parseClickableAnnotation(values, clickables)
            ANNOTATION_TYPE_SIZE_ABSOLUTE -> parseSizeAbsoluteAnnotation(context, values)
            else -> null
        }

    /**
     * Splits annotation value of type `value1[|value2][|value3]...` into list of
     * separate atomic values and reduces repeated entries.
     */
    protected open fun parseAnnotationValue(value: String): List<String> =
        value
            .split("|")
            .distinct()

    /**
     * Tries to parse annotation value as value argument.
     * If done successfully, returns index of this value argument, `null` otherwise.
     */
    protected open fun parseAnnotationValueArg(value: String): Int? {
        val parts = value.split("$", limit = 2)
        if (parts.size == 2 && parts.first() == "arg") {
            parts[1].toIntOrNull()?.let { return it }
        }
        Log.w(StringAnnotations.TAG, "Invalid annotation value argument format")
        return null
    }

    private fun parseBackgrounAnnotation(
        context: Context,
        values: List<String>,
        valueArgs: Array<out Any>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use very first one
        val color = parseAnnotationValueArg(value)
        return parseColorAttributeValue(context, value)?.let { color ->
            BackgroundColorSpan(color)
        }
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<String>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use very first one
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
        val index = values.firstOrNull()?.toIntOrNull() ?: return null
        return clickables.getOrNull(index)
    }

    private fun parseSizeAbsoluteAnnotation(
        context: Context,
        values: List<String>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null
        val size = parseSize(context, value) ?: return null
        return AbsoluteSizeSpan(size)
    }

    /**
     * Parses string [value] of any color attribute (like [ANNOTATION_TYPE_FOREGROUND] or
     * [ANNOTATION_TYPE_BACKGROUND]) into color integer.
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
                    StringAnnotations.TAG,
                    "String annotation with attribute value=\"$value\" can not be parsed into valid color"
                )
                null // return null, if attribute value is invalid
            }
        }

    /**
     * Parses [value] of format `"{NUMBER}{UNIT}"` into pixel size.
     *
     * @see splitSize
     */
    @Px
    private fun parseSize(
        context: Context,
        value: String
    ): Int? {
        val pair = splitSize(value) ?: return null
        return parseSize(context, pair.first, pair.second)
    }

    /**
     * Parses [size], specified in [unit] into pixel equivalent size.
     */
    @Px
    protected open fun parseSize(
        context: Context,
        size: Float,
        unit: String
    ): Int? =
        when (unit) {
            ANNOTATION_VALUE_UNIT_PX, "" -> size.toInt() // already pixels, but just float
            ANNOTATION_VALUE_UNIT_SP -> parseSizeUnit(context, size, TypedValue.COMPLEX_UNIT_SP)
            ANNOTATION_VALUE_UNIT_DP -> parseSizeUnit(context, size, TypedValue.COMPLEX_UNIT_DIP)
            else -> null // unknown dimension
        }

    /**
     * Splites complex size [value], specified in units into pair of float size and unit label, i.e.
     * `"18.56sp"` -> `{18.56f, "sp"}`.
     */
    protected open fun splitSize(value: String): Pair<Float, String>? {
        val unit = value.takeLastWhile { it.isLetter() }
        val size = value
            .substring(0, value.length - unit.length)
            .toFloatOrNull()
            ?: return null
        return Pair(size, unit)
    }

    /**
     * Converts [size], specified in [unit] into pixel equivalent size.
     */
    @Px
    protected fun parseSizeUnit(
        context: Context,
        size: Float,
        unit: Int
    ): Int {
        return TypedValue.applyDimension(
            /*unit*/ unit,
            /*value*/ size,
            /*metrics*/ context.resources.displayMetrics
        ).toInt()
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

    /**
     * Retrieves value argument at [index] in [valueArgs] array.
     *
     * If there is no such index in array, or retrieved element is `null`,
     * then appropriate message will be logged with [Log.WARN] priority.
     */
    private fun getValueArgumentOrLog(
        valueArgs: Array<out Any>,
        index: Int
    ): Any? =
        valueArgs.getOrNull(index).also { arg ->
            arg ?: Log.w(StringAnnotations.TAG, "There is no value argument at index=$index")
        }

    /**
     * Logs message of inability to parse annotation with specified [type] and [value]
     * into valid span with [Log.WARN] priority.
     */
    protected fun logAnnotationParsingWarning(
        type: String,
        value: String
    ) {
        Log.w(
            StringAnnotations.TAG,
            "String annotation with attribute=\"$type\" and value=\"$value\" cannot be parsed into valid span"
        )
    }

    private companion object {

        // types
        const val ANNOTATION_TYPE_BACKGROUND = "background"
        const val ANNOTATION_TYPE_FOREGROUND = "color"
        const val ANNOTATION_TYPE_STYLE = "style"
        const val ANNOTATION_TYPE_CLICKABLE = "clickable"
        const val ANNOTATION_TYPE_SIZE_ABSOLUTE = "size-absolute"

        // values
        const val ANNOTATION_VALUE_TYPEFACE_STYLE_BOLD = "bold"
        const val ANNOTATION_VALUE_TYPEFACE_STYLE_ITALIC = "italic"
        const val ANNOTATION_VALUE_TYPEFACE_STYLE_NORMAL = "normal"

        const val ANNOTATION_VALUE_UNDERLINE = "underline"
        const val ANNOTATION_VALUE_STRIKETHROUGH = "strikethrough"

        // misc
        const val ANNOTATION_VALUE_UNIT_PX = "px"
        const val ANNOTATION_VALUE_UNIT_SP = "sp"
        const val ANNOTATION_VALUE_UNIT_DP = "dp"
    }
}