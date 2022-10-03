package com.mmolosay.stringannotations.processor.parser

import com.mmolosay.shared.service.Logger
import com.mmolosay.stringannotations.args.Arguments
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
 * Default implementation of [AnnotationValueParser].
 * Accepts placeholders of `"$arg${TYPE}${INDEX}"` format.
 */
public class DefaultAnnotationValueParser(
    private val logger: Logger
) : AnnotationValueParser {

    override fun <V> parse(token: Token, arguments: Arguments<V>): V? =
        parse(token.string, arguments)

    /**
     * Tries to parse [placeholder] into some value from [arguments] list.
     *
     * Steps:
     * 1. break placeholder into list of tokens, according to its format.
     * 2. check that placeholder's type matches qualifier of [arguments].
     * 3. get argument index via [parsePlaceholderIndex].
     * 4. return argument at parsed index in [arguments] list.
     *
     * @param placeholder annotation tag value placeholder of `"$arg${TYPE}${INDEX}"` format.
     * @param arguments source of values.
     *
     * @return argument from [arguments] at placeholder's parsed index.
     */
    private fun <V> parse(placeholder: String, arguments: Arguments<V>): V? =
        try {
            require(placeholder.startsWith('$'))
            val tokens = placeholder.substring(1).split("$", limit = 4)
            val (prefix, qualifier, ordinal) = tokens
            require(tokens.size == 3)
            require(prefix == "arg")
            require(qualifier == arguments.qualifier)
            val index = requireNotNull(parsePlaceholderIndex(ordinal))
            getArgument(index, arguments)
        } catch (e: Exception) {
            logger.w("Invalid annotation argument placeholder format: \"$placeholder\"")
            null
        }

    private fun parsePlaceholderIndex(ordinal: String): Int? =
        ordinal.toIntOrNull().also {
            it ?: logger.w("Cannot parse \"$ordinal\" as argument index")
        }

    private fun <V> getArgument(index: Int, args: Arguments<V>): V? =
        args.getOrNull(index).also {
            it ?: logger.w("There is no value argument at index=$index")
        }
}