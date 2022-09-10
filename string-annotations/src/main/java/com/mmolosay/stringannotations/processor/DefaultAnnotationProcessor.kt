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
import com.mmolosay.stringannotations.internal.Logger
import com.mmolosay.stringannotations.parser.AnnotationValueParser
import com.mmolosay.stringannotations.parser.ColorValueParser
import com.mmolosay.stringannotations.parser.SizeUnitValueParser
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

    protected open val valueArgParser: ValueArgParser =
        DefaultValueArgParser()

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: ValueArgs
    ): CharacterStyle? {
        val type = annotation.key
        val value = annotation.value
        val values = split(value)
        return parseAnnotation(context, type, values, args).also { span ->
            span ?: Logger.w(
                "Annotation with attribute=\"$type\" and value=\"$value\" " +
                    "cannot be parsed into valid span. " +
                    "Make sure attribute and its value are correct and supported."
            )
        }
    }

    /**
     * Splits annotation value of format `"value1[value2][value3][...]"` into list of
     * separate atomic values.
     *
     * This implementation also reduces repeated values.
     */
    protected open fun split(value: String): List<String> =
        value.split("|").distinct()

    /**
     * Implementation of [parseAnnotation] with provided [Annotation]'s key and values.
     *
     * Derived class should override this method and call super's implementation at the beginning
     * in order to parse custom annotation type.
     *
     * @param context caller context.
     * @param type annotation's tag attribute name.
     * @param values list of atomic annotation tag values.
     * @param args list of runtime value arguments.
     */
    protected open fun parseAnnotation(
        context: Context,
        type: String,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        return when (type) {
            ANNOTATION_TYPE_BACKGROUND -> parseBackgroundAnnotation(context, values, args)
            ANNOTATION_TYPE_FOREGROUND -> parseForegroundAnnotation(context, values, args)
            ANNOTATION_TYPE_STYLE -> parseStyleAnnotation(context, values, args)
            ANNOTATION_TYPE_CLICKABLE -> parseClickableAnnotation(context, values, args)
            ANNOTATION_TYPE_SIZE_ABSOLUTE -> parseSizeAbsoluteAnnotation(context, values, args)
            else -> null
        }
    }

    private fun <V> parseValues(
        context: Context,
        type: String,
        values: List<String>,
        parser: AnnotationValueParser<V>?,
        args: List<V>,
        strategy: ValuesPickingStrategy,
        reducer: ValuesReducer<V>
    ): V? =
        values.asSequence()
            .mapNotNull { parseValue(context, type, it, parser, args) }
            .let { strategy.pick(it) }
            .let { reducer.reduce(it) }

    private fun <V> parseValue(
        context: Context,
        type: String,
        value: String,
        parser: AnnotationValueParser<V>?,
        args: List<V>
    ): V? =
        parser?.parse(context, value)
            ?: valueArgParser.parse(value, type, args)

    // region Annotation type parsing

    private fun parseBackgroundAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val color = parseValues(
            context = context,
            type = ANNOTATION_TYPE_BACKGROUND,
            values = values,
            parser = ColorValueParser,
            args = args.colors,
            strategy = ValuesPickingStrategy.First,
            reducer = ValuesReducer.Single()
        ) ?: return null
        return BackgroundColorSpan(color)
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val color = parseValues(
            context = context,
            type = ANNOTATION_TYPE_FOREGROUND,
            values = values,
            parser = ColorValueParser,
            args = args.colors,
            strategy = ValuesPickingStrategy.First,
            reducer = ValuesReducer.Single()
        ) ?: return null
        return ForegroundColorSpan(color)
    }

    private fun parseStyleAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? =
        when {
            values.contains(ANNOTATION_VALUE_UNDERLINE) -> UnderlineSpan()
            values.contains(ANNOTATION_VALUE_STRIKETHROUGH) -> StrikethroughSpan()
            else -> parseTypefaceStyleAnnotation(context, values, args)
        }

    private fun parseTypefaceStyleAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val style = parseValues(
            context = context,
            type = ANNOTATION_TYPE_STYLE,
            values = values,
            parser = TypefaceStyleValueParser,
            args = args.typefaceStyles,
            strategy = ValuesPickingStrategy.All,
            reducer = ValuesReducer.Multiple(TypefaceStyleValueParser::reduceTypefaceStyles)
        ) ?: return null
        return StyleSpan(style)
    }

    private fun parseClickableAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? =
        parseValues(
            context = context,
            type = ANNOTATION_TYPE_CLICKABLE,
            values = values,
            parser = null,
            args = args.clickables,
            strategy = ValuesPickingStrategy.First,
            reducer = ValuesReducer.Single()
        )

    private fun parseSizeAbsoluteAnnotation(
        context: Context,
        values: List<String>,
        args: ValueArgs
    ): CharacterStyle? {
        val size = parseValues(
            context = context,
            type = ANNOTATION_TYPE_SIZE_ABSOLUTE,
            values = values,
            parser = SizeUnitValueParser,
            args = args.absSizes,
            strategy = ValuesPickingStrategy.First,
            reducer = ValuesReducer.Single()
        ) ?: return null
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