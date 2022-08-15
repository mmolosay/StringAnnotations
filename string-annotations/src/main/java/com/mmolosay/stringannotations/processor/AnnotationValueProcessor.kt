package com.mmolosay.stringannotations.processor

import com.mmolosay.stringannotations.core.Logger

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
 * `typealias` for functions, that can process argument placeholder value of string annotation
 * into its corresponding argument.
 */
internal typealias ArgPlaceholderProcessor<T> = (placeholder: String) -> T?

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
     * Tries to parse annotation value as placeholder for argument of type [T].
     *
     * If done successfully, returns argument at parsed index in [args], casted to [T],
     * or `null` otherwise.
     */
    public fun <T> parsePlaceholderAs(placeholder: String, args: Array<out Any>): T?
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
     * Uses pattern `"arg${INDEX}"` for placeholders.
     *
     * Will log appropriate message, if there is no argument at parsed index.
     *
     * @see AnnotationValueProcessor.parsePlaceholderAs
     */
    public override fun <T> parsePlaceholderAs(placeholder: String, args: Array<out Any>): T? {
        val parts = placeholder.split("$", limit = 3)
        return if (parts.size == 2 && parts.first() == "arg") {
            val index = parsePlaceholderIndex(parts[1]) ?: return null
            val arg = getArg(args, index) ?: return null
            castArg(arg)
        } else {
            Logger.w("Invalid annotation value argument format")
            null
        }
    }

    private fun parsePlaceholderIndex(value: String): Int? =
        value.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$value\" as argument index")
        }

    private fun getArg(args: Array<out Any>, index: Int): Any? =
        args.getOrNull(index).also {
            it ?: Logger.w("There is no value argument at index=$index")
        }

    @Suppress("UNCHECKED_CAST")
    private fun <T> castArg(arg: Any): T? =
        (arg as? T).also {
            it ?: Logger.w("Cannot cast value argument to specified type")
        }
}