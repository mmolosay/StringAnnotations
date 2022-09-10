package com.mmolosay.stringannotations.processor.values

import com.mmolosay.stringannotations.internal.Logger

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
 * Default implementation of [ValuesArgParser].
 */
public class DefaultValuesArgParser : ValuesArgParser {

    /**
     * Tries to infer argument from [args] list for specified [placeholder].
     * Placeholder must have [expected] type.
     *
     * Steps:
     * 1. break placeholder into meaningful parts according to its format.
     * 2. get placeholder type via [parsePlaceholderType].
     * 3. get placeholder index via [parsePlaceholderIndex].
     * 4. check, that actual parsed type is equal to [expected] one.
     * 5. return argument at parsed index in [args] list.
     *
     * @param placeholder annotation tag value placeholder of `"$arg${TYPE}${INDEX}"` format.
     * @param expected expected type of [placeholder].
     * @param args list to get argument from.
     *
     * @return argument from [args] at placeholder's parsed index.
     */
    override fun <T> parse(placeholder: String, expected: String, args: List<T>): T? {
        try {
            require(placeholder.startsWith('$')) // starts with $ sign
            val parts = placeholder.substring(1).split("$", limit = 4)
            if (parts.size == 3 && parts[0] == "arg") {
                val type = parsePlaceholderType(parts[1]) ?: return null
                val index = parsePlaceholderIndex(parts[2]) ?: return null
                if (checkPlaceholderType(type, expected)) {
                    return getArg(args, index)
                }
            }
        } catch (e: Exception) {
            // catch all exceptions, log below
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
}