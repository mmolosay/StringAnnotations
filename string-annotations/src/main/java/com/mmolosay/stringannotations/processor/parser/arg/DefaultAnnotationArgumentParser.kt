package com.mmolosay.stringannotations.processor.parser.arg

import com.mmolosay.stringannotations.internal.Logger
import com.mmolosay.stringannotations.processor.token.Token

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
 * Default implementation of [AnnotationArgumentParser].
 */
public object DefaultAnnotationArgumentParser : AnnotationArgumentParser {

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

    private fun <V> parse(placeholder: String, args: List<V>): V? =
        try {
            require(placeholder.startsWith('$'))
            val tokens = placeholder.substring(1).split("$", limit = 4)
            val (prefix, qualifier, ordinal) = tokens
            require(tokens.size == 3)
            require(prefix == "arg")
            require(true) // TODO: qualifier
            val index = requireNotNull(parsePlaceholderIndex(ordinal))
            getArg(index, args)
        } catch (e: Exception) {
            Logger.w("Invalid annotation argument placeholder format: \"$placeholder\"")
            null
        }

    private fun parsePlaceholderIndex(ordinal: String): Int? =
        ordinal.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$ordinal\" as argument index")
        }

    private fun <V> getArg(index: Int, args: List<V>): V? =
        args.getOrNull(index).also {
            it ?: Logger.w("There is no value argument at index=$index")
        }
}