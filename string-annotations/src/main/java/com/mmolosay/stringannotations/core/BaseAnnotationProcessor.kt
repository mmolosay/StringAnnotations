package com.mmolosay.stringannotations.core

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.parser.TokenParser
import com.mmolosay.stringannotations.values.Evaluator
import com.mmolosay.stringannotations.values.ValueArgParser

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
 * Base class for [AnnotationProcessor] implementations.
 * Utilizes a lot of usefull funtionality, making implementing custom [AnnotationProcessor] easier.
 */
public abstract class BaseAnnotationProcessor<V> : AnnotationProcessor {

    protected abstract val tokenizer: Tokenizer
    protected abstract val tokenParser: TokenParser<V>?
    protected abstract val valueArgParser: ValueArgParser
    protected abstract val evaluator: Evaluator<V>

    override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: ValueArgs?
    ): CharacterStyle? {
        val tokens = tokenizer.tokenize(annotation.value)
        return parseAnnotation(context, tokens, args)
    }

    public open fun parseAnnotation(
        context: Context,
        tokens: Sequence<AnnotationTag.Token>,
        args: ValueArgs?
    ): CharacterStyle? {
        val values =
            tokens.mapNotNull { token ->
                tokenParser?.parse(context, token)
                    ?: inferArgs(args)?.let { valueArgParser.parse(token, it) }
            }
        val value = evaluator.evaluate(values) ?: return null
        return makeSpan(value)
    }

    protected abstract fun inferArgs(args: ValueArgs?): List<V>?

    protected abstract fun makeSpan(value: V): CharacterStyle?
}