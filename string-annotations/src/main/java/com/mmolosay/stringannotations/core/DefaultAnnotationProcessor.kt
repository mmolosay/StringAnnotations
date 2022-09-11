package com.mmolosay.stringannotations.core

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
import com.mmolosay.stringannotations.values.DefaultValueArgParser
import com.mmolosay.stringannotations.values.ValueArgParser
import com.mmolosay.stringannotations.values.ValuesProcessor

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
        val values = getAnnotationValues(annotation)
        return parseAnnotation(context, type, values, args).also { span ->
            span ?: Logger.w(
                "Annotation with attribute=\"${annotation.key}\" and value=\"${annotation.value}\" " +
                    "cannot be parsed into valid span. " +
                    "Make sure attribute and its value are correct and supported."
            )
        }
    }

    /**
     * Splits annotation value of format `"value1[|value2][|value3]..."` into list of
     * separate atomic values.
     *
     * This implementation also reduces repeated values.
     */
    // TODO: should be performed individually for every annotation, according to its type
    private fun getAnnotationValues(annotation: Annotation): List<AnnotationValue> =
        annotation.value
            .split("|")
            .distinct()
            .map { AnnotationValue(it) }

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
        values: List<AnnotationValue>,
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

    /**
     * Parses and processes specified [values] into result value of type [V].
     *
     * 1. Try and map [values] into new list of [V] using [parser],
     * skipping unparseable values (see [parseValue]).
     * 2. Process parsed values from step 1 into final result using [processor].
     *
     * @param context caller context.
     * @param type annotation's tag attribute name.
     * @param values list of atomic annotation tag values.
     * @param args list of runtime value arguments.
     * @param parser parser, that will be used to parse string into value of type [V].
     * @param processor processor, that will be used to obtain result value.
     */
    protected fun <V> processValues(
        context: Context,
        type: String,
        values: List<AnnotationValue>,
        args: List<V>,
        parser: AnnotationValueParser<V>?,
        processor: ValuesProcessor<V>
    ): V? =
        values.asSequence()
            .mapNotNull { parseValue(context, type, it, args, parser) }
            .let { processor.process(it) }

    /**
     * Tries to parse specified [value] into new one of type [V].
     *
     * First, it will try and parse [value] using appropriate [parser].
     * Then, if it wasn't successful, will try and parse [value] as a placeholder
     * for a value argument using [valueArgParser].
     */
    private fun <V> parseValue(
        context: Context,
        type: String,
        value: AnnotationValue,
        args: List<V>,
        parser: AnnotationValueParser<V>?
    ): V? =
        parser?.parse(context, value)
            ?: valueArgParser.parse(value, type, args)

    // region Annotation type parsing

    private fun parseBackgroundAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? {
        val color = processValues(
            context = context,
            type = ANNOTATION_TYPE_BACKGROUND,
            values = values,
            args = args.colors,
            parser = ColorValueParser,
            processor = ValuesProcessor.Single()
        ) ?: return null
        return BackgroundColorSpan(color)
    }

    private fun parseForegroundAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? {
        val color = processValues(
            context = context,
            type = ANNOTATION_TYPE_FOREGROUND,
            values = values,
            args = args.colors,
            parser = ColorValueParser,
            processor = ValuesProcessor.Single()
        ) ?: return null
        return ForegroundColorSpan(color)
    }

    private fun parseStyleAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? =
        when {
            values.contains(valueUnderline) -> UnderlineSpan()
            values.contains(valueStrikethrough) -> StrikethroughSpan()
            else -> parseTypefaceStyleAnnotation(context, values, args)
        }

    private fun parseTypefaceStyleAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? {
        val style = processValues(
            context = context,
            type = ANNOTATION_TYPE_STYLE,
            values = values,
            args = args.typefaceStyles,
            parser = TypefaceStyleValueParser,
            processor = ValuesProcessor.All(TypefaceStyleValueParser::reduceTypefaceStyles)
        ) ?: return null
        return StyleSpan(style)
    }

    private fun parseClickableAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? =
        processValues(
            context = context,
            type = ANNOTATION_TYPE_CLICKABLE,
            values = values,
            args = args.clickables,
            parser = null,
            processor = ValuesProcessor.Single()
        )

    private fun parseSizeAbsoluteAnnotation(
        context: Context,
        values: List<AnnotationValue>,
        args: ValueArgs
    ): CharacterStyle? {
        val size = processValues(
            context = context,
            type = ANNOTATION_TYPE_SIZE_ABSOLUTE,
            values = values,
            parser = SizeUnitValueParser,
            args = args.absSizes,
            processor = ValuesProcessor.Single()
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

        // constant values
        val valueUnderline = AnnotationValue("underline")
        val valueStrikethrough = AnnotationValue("strikethrough")
    }
}