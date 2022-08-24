package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.core.Logger
import com.mmolosay.stringannotations.parser.TypefaceStyleValueParser

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
 * ```
 *
 * ### Clickable
 *
 * Annotation, that specifies ability of its body to intercept click events.
 *
 * Use runtime value arguments to specify span for the annotation.
 *
 * You should also explicitly specify, that your `TextView` contains clickable text
 * by calling [android.widget.TextView.setMovementMethod].
 *
 * ```
 * <annotation clickable="arg$clickable$0">clickable text</annotation>
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

    /**
     * Annotation value processor to be used in order to parse values of different annotation types.
     */
    protected open val valueProcessor: AnnotationValueProcessor =
        DefaultAnnotationValueProcessor()

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: ValueArgs
    ): CharacterStyle? {
        val type = annotation.key
        val value = annotation.value
        val values = valueProcessor.split(value)
        return parseAnnotation(context, type, values, args).also { span ->
            span ?: Logger.w(
                "Annotation with attribute=\"$type\" and value=\"$value\" " +
                    "cannot be parsed into valid span. " +
                    "Make sure attribute and its value are correct and supported."
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
    protected open fun parseAnnotation(
        context: Context,
        type: String,
        values: List<String>,
        args: ValueArgs
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
        args: ValueArgs
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        return valueProcessor.parseColor(value, args.colors)?.let { color ->
            BackgroundColorSpan(color)
        }
    }

    private fun parseForegroundAnnotation(
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        return valueProcessor.parseColor(value, args.colors)?.let { color ->
            ForegroundColorSpan(color)
        }
    }

    private fun parseStyleAnnotation(
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? =
        when {
            values.contains(ANNOTATION_VALUE_UNDERLINE) -> UnderlineSpan()
            values.contains(ANNOTATION_VALUE_STRIKETHROUGH) -> StrikethroughSpan()
            else -> parseTypefaceStyleAnnotation(values, args)
        }

    private fun parseTypefaceStyleAnnotation(
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val styles = values.mapNotNull { value ->
            valueProcessor.parseTypefaceStyle(value, args.typefaceStyles)
        }
        val style = TypefaceStyleValueParser.reduceTypefaceStyles(styles) ?: return null
        return StyleSpan(style)
    }

    private fun parseClickableAnnotation(
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val placeholder = values.firstOrNull() ?: return null // use only first one
        return valueProcessor.parseClickable(placeholder, args.clickables)
    }

    private fun parseSizeAbsoluteAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val value = values.firstOrNull() ?: return null // use only first one
        val metrics = context.resources.displayMetrics
        return valueProcessor.parseAbsoluteSize(value, args.absSizes, metrics)?.let { size ->
            AbsoluteSizeSpan(size)
        }
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