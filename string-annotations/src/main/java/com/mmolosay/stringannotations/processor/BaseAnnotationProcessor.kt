package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.core.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.parser.ValueParser
import com.mmolosay.stringannotations.processor.parser.arg.AnnotationArgumentParser
import com.mmolosay.stringannotations.processor.token.Tokenizer

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
public abstract class BaseAnnotationProcessor<V, A> : AnnotationProcessor<A> {

    protected abstract val tokenizer: Tokenizer
    protected abstract val valueParser: ValueParser<V>?
    protected abstract val argParser: AnnotationArgumentParser
    protected abstract val conflator: ValuesConfaltor<V>

    override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        arguments: A?
    ): CharacterStyle? {
        val tokens = tokenizer.tokenize(annotation.value)
        val values = tokens
            .mapNotNull { token ->
                valueParser?.parse(context, token)
                    ?: inferArguments(arguments)?.let { argParser.parse(token, it) }
            }
        val value = conflator.conflate(values) ?: return null
        return makeSpan(value)
    }

    /**
     * Obtains list of appropiate for type of this annotation processor values from [set].
     */
    protected abstract fun inferArguments(set: A?): Arguments<V>?

    /**
     * Creates new instance of span, corresponding to type of this annotation processor.
     */
    protected abstract fun makeSpan(value: V): CharacterStyle?
}