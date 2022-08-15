package com.mmolosay.stringannotations.processor

import com.mmolosay.stringannotations.core.Logger

/**
 * Processes values of string annotation tags.
 */
public interface AnnotationValueProcessor {

    /**
     * Splits annotation value of type `value1[value2][value3][...]` into list of
     * separate atomic values.
     */
    public fun split(value: String): List<String>

    /**
     * Tries to parse annotation value as value argument of type [T].
     *
     * If done successfully, returns argument at parsed index in [args], casted to [T],
     * or `null` otherwise.
     */
    public fun <T> parseArgAs(value: String, args: List<Any>): T?
}

public object DefaultAnnotationValueProcessor : AnnotationValueProcessor {

    /**
     * This implementation also reduces repeated values.
     *
     * @see AnnotationValueProcessor.split
     */
    public override fun split(value: String): List<String> =
        value.split("|").distinct()


    /**
     * Uses pattern `"arg${INDEX}"`.
     *
     * Will log appropriate message, if there is no argument at parsed index.
     *
     * @see AnnotationValueProcessor.parseArgAs
     */
    public override fun <T> parseArgAs(value: String, args: List<Any>): T? {
        val parts = value.split("$", limit = 3)
        return if (parts.size == 2 && parts.first() == "arg") {
            val index = parseArgIndex(parts[1]) ?: return null
            val arg = getArg(args, index) ?: return null
            castArg(arg)
        } else {
            Logger.w("Invalid annotation value argument format")
            null
        }
    }

    private fun parseArgIndex(value: String): Int? =
        value.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$value\" as argument index")
        }

    private fun getArg(args: List<Any>, index: Int): Any? =
        args.getOrNull(index).also {
            it ?: Logger.w("There is no value argument at index=$index")
        }

    @Suppress("UNCHECKED_CAST")
    private fun <T> castArg(arg: Any): T? =
        (arg as? T).also {
            it ?: Logger.w("Cannot cast value argument to specified type")
        }
}