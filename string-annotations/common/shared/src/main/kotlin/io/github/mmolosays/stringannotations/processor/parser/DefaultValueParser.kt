package io.github.mmolosays.stringannotations.processor.parser

import io.github.mmolosays.stringannotations.Logger
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList

/*
 * Copyright 2023 Mikhail Malasai
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
 * Default implementation of [ValueParser].
 * Accepts placeholders of `"$arg${QUALIFIER}${INDEX}"` format.
 */
public object DefaultValueParser : ValueParser {

    /**
     * Tries to parse [placeholder] into some value from [values] list.
     *
     * Steps:
     * 1. break placeholder into list of tokens, according to its format.
     * 2. check that placeholder's type matches qualifier of [values].
     * 3. get argument index via [parsePlaceholderIndex].
     * 4. return argument at parsed index in [values] list.
     *
     * @param placeholder annotation tag value placeholder of `"$arg${TYPE}${INDEX}"` format.
     * @param values source of values.
     *
     * @return argument from [values] at placeholder's parsed index.
     */
    override fun <V> parse(placeholder: String, values: QualifiedList<V>): V? =
        try {
            require(placeholder.startsWith('$'))
            val tokens = placeholder.substring(1).split("$", limit = 4)
            val (prefix, qualifier, ordinal) = tokens
            require(tokens.size == 3)
            require(prefix == "arg")
            require(qualifier == values.qualifier)
            val index = requireNotNull(parsePlaceholderIndex(ordinal))
            getArgument(index, values)
        } catch (e: Exception) {
            Logger.w("Invalid annotation argument placeholder format: \"$placeholder\"")
            null
        }

    private fun parsePlaceholderIndex(ordinal: String): Int? =
        ordinal.toIntOrNull().also {
            it ?: Logger.w("Cannot parse \"$ordinal\" as argument index")
        }

    private fun <V> getArgument(index: Int, args: QualifiedList<V>): V? =
        args.getOrNull(index).also {
            it ?: Logger.w("There is no annotation argument at index=$index")
        }
}