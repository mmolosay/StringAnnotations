package com.mmolosay.stringannotations.processor.parser.arg

import com.mmolosay.stringannotations.processor.token.Token
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
 * Default implementation of [ValueArgParser].
 */
public object DefaultValueArgParser : ValueArgParser {

    /**
     * Tries to infer argument from [args] list for specified [token].
     *
     * Steps:
     * 1. break placeholder into meaningful parts according to its format.
     * 2. get placeholder index via [parsePlaceholderIndex].
     * 3. return argument at parsed index in [args] list.
     *
     * @param token annotation tag value placeholder of `"$arg${TYPE}${INDEX}"` format.
     * @param args list to get argument from.
     *
     * @return argument from [args] at placeholder's parsed index.
     */
    override fun <V> parse(token: Token, args: List<V>): V? =
        parse(token.string, args)

    private fun <V> parse(value: String, args: List<V>): V? {
        try {
            require(value.startsWith('$')) // starts with $ sign
            val parts = value.substring(1).split("$", limit = 4)
            if (parts.size == 3 && parts[0] == "arg") {
                val index = parsePlaceholderIndex(parts[2]) ?: return null
                return getArg(args, index)
            }
        } catch (e: Exception) {
            // catch all exceptions, log below
        }
        Logger.w("Invalid annotation value placeholder format: \"$value\"")
        return null
    }

    private fun parsePlaceholderIndex(value: String): Int? =
        value.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$value\" as argument index")
        }

    private fun <T> getArg(source: List<T>, index: Int): T? =
        source.getOrNull(index).also {
            it ?: Logger.w("There is no value argument at index=$index")
        }
}