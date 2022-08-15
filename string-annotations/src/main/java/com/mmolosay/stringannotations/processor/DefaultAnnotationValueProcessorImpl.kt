package com.mmolosay.stringannotations.processor

import android.text.style.ClickableSpan
import android.util.DisplayMetrics
import com.mmolosay.stringannotations.core.Logger
import com.mmolosay.stringannotations.parser.ColorValueParser
import com.mmolosay.stringannotations.parser.SizeUnitValueParser
import com.mmolosay.stringannotations.parser.TypefaceStyleParser

public open class DefaultAnnotationValueProcessorImpl : DefaultAnnotationValueProcessor {

    /**
     * This implementation also reduces repeated values.
     *
     * @see DefaultAnnotationValueProcessor.split
     */
    public override fun split(values: String): List<String> =
        values.split("|").distinct()

    public override fun parseColor(value: String, args: List<Int>): Int? =
        ColorValueParser.parse(value)
            ?: getArgFor(value, PLACEHOLDER_TYPE_COLOR, args)

    override fun parseTypefaceStyle(value: String, args: List<Int>): Int? =
        TypefaceStyleParser.parse(value)
            ?: getArgFor(value, PLACEHOLDER_TYPE_TYPEFACE_STYLE, args)

    override fun parseClickable(placeholder: String, args: List<ClickableSpan>): ClickableSpan? =
        getArgFor(placeholder, PLACEHOLDER_TYPE_CLICKABLE, args)

    override fun parseAbsoluteSize(value: String, args: List<Int>, metrics: DisplayMetrics): Int? =
        SizeUnitValueParser.parse(value, metrics)
            ?: getArgFor(value, PLACEHOLDER_TYPE_ABSOLUTE_SIZE, args)

    /**
     * Tries to infer argument from [args] list for specified [placeholder].
     * Placeholder must have [expected] type.
     *
     * @param placeholder annotation tag value placeholder of `"arg${TYPE}${INDEX}"` format.
     * @param expected expected type of [placeholder].
     * @param args list to get argument from.
     *
     * @return argument from [args] at placeholder's parsed index.
     */
    private fun <T> getArgFor(placeholder: String, expected: String, args: List<T>): T? {
        try {
            val parts = placeholder.split("$", limit = 4)
            if (parts.size == 3 && parts[0] == "arg") {
                val type = parsePlaceholderType(parts[1]) ?: return null
                val index = parsePlaceholderIndex(parts[2]) ?: return null
                if (checkPlaceholderType(type, expected)) {
                    return getArg(args, index)
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            // empty, will log below
        }
        Logger.w("Invalid annotation value placeholder format: \"$placeholder\"")
        return null
    }

    private fun parsePlaceholderType(type: String): String? =
        type.ifEmpty {
            Logger.w("Invalid placeholder type=\"$type\"")
            null
        }

    private fun parsePlaceholderIndex(value: String): Int? =
        value.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$value\" as argument index")
        }

    private fun checkPlaceholderType(actual: String, expected: String): Boolean =
        (actual == expected).also { equals ->
            if (!equals) Logger.w("Requested \"${expected}\" type from \"$actual\" placeholder")
        }

    private fun <T> getArg(source: List<T>, index: Int): T? =
        source.getOrNull(index).also {
            it ?: Logger.w("There is no value argument at index=$index")
        }

    private companion object {
        const val PLACEHOLDER_TYPE_COLOR = "color"
        const val PLACEHOLDER_TYPE_CLICKABLE = "clickable"
        const val PLACEHOLDER_TYPE_TYPEFACE_STYLE = "style"
        const val PLACEHOLDER_TYPE_ABSOLUTE_SIZE = "size-absolute"
    }
}