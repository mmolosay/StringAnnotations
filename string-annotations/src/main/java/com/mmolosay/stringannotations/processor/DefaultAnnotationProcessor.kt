package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.core.Logger
import com.mmolosay.stringannotations.parser.ColorValueParser
import com.mmolosay.stringannotations.parser.SizeUnitValueParser
import com.mmolosay.stringannotations.parser.TypefaceStyleParser

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

    protected open val valueProcessor: AnnotationValueProcessor = DefaultAnnotationValueProcessor

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: Array<out Any>
    ): CharacterStyle? {
        val type = annotation.key
        val value = annotation.value
        val values = valueProcessor.split(value)
        return parseAnnotation(context, type, values, args).also { span ->
            span ?: Logger.w(
                "Annotation with attribute=\"$type\" and value=\"$value\" " +
                    "cannot be parsed into valid span"
            )
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
     * @param values list of atomic annotation tag values, obtained from [AnnotationValueProcessor.split].ns.
     * @param args list of runtime value arguments.
     */
    internal open fun parseAnnotation(
        context: Context,
        type: String,
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        return when (type) {
            ANNOTATION_TYPE_BACKGROUND -> parseBackgroundAnnotation(values, args)
            ANNOTATION_TYPE_FOREGROUND -> parseForegroundAnnotation(values, args)
            ANNOTATION_TYPE_STYLE -> parseStyleAnnotation(values, args)
            ANNOTATION_TYPE_CLICKABLE -> parseClickableAnnotation(values, args)
            ANNOTATION_TYPE_SIZE_ABSOLUTE -> parseSizeAbsoluteAnnotation(context, values, args)
            else -> null
        }
    }

    // region Annotation type parsing

    private fun parseBackgroundAnnotation(
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        val color = valueProcessor.parsePlaceholderAs<Int>(value, args)
            ?: ColorValueParser.parse(value)
            ?: return null
        return BackgroundColorSpan(color)
    }

    private fun parseForegroundAnnotation(
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        val color = valueProcessor.parsePlaceholderAs<Int>(value, args)
            ?: ColorValueParser.parse(value)
            ?: return null
        return ForegroundColorSpan(color)
    }

    private fun parseStyleAnnotation(
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? =
        when {
            values.contains(ANNOTATION_VALUE_UNDERLINE) -> UnderlineSpan()
            values.contains(ANNOTATION_VALUE_STRIKETHROUGH) -> StrikethroughSpan()
            else -> parseTypefaceStyleAnnotation(values, args)
        }

    private fun parseTypefaceStyleAnnotation(
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        val style = TypefaceStyleParser.parse(values) { placeholder ->
            valueProcessor.parsePlaceholderAs(placeholder, args)
        } ?: return null
        return StyleSpan(style)
    }

    private fun parseClickableAnnotation(
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        return valueProcessor.parsePlaceholderAs<ClickableSpan>(value, args)
    }

    private fun parseSizeAbsoluteAnnotation(
        context: Context,
        values: List<String>,
        args: Array<out Any>
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        val size = valueProcessor.parsePlaceholderAs<Int>(value, args)
            ?: SizeUnitValueParser.parse(value, context.resources.displayMetrics)
            ?: return null
        return AbsoluteSizeSpan(size)
    }

    // endregion

    private companion object {

        // types
        const val ANNOTATION_TYPE_BACKGROUND = "background"
        const val ANNOTATION_TYPE_FOREGROUND = "color"
        const val ANNOTATION_TYPE_STYLE = "style"
        const val ANNOTATION_TYPE_CLICKABLE = "clickable"
        const val ANNOTATION_TYPE_SIZE_ABSOLUTE = "size-absolute"

        // values
        const val ANNOTATION_VALUE_UNDERLINE = "underline"
        const val ANNOTATION_VALUE_STRIKETHROUGH = "strikethrough"
    }
}