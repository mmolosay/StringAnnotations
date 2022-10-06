package com.mmolosay.stringannotations.processor

import android.text.Annotation
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.parser.DefaultValuesParser
import com.mmolosay.stringannotations.processor.parser.ValuesParser
import com.mmolosay.stringannotations.processor.token.Token
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
 * Abstract class for [AnnotationProcessor] implementations for single annotation type.
 * Utilizes a lot of usefull funtionality, making implementing custom [AnnotationProcessor] easier.
 *
 * @param V type of annotation values; for instance, `Int` for color.
 * @param S type of spans.
 */
public abstract class AbstractAnnotationProcessor<A, V, S> : AnnotationProcessor<A, S> {

    protected abstract val tokenizer: Tokenizer
    protected abstract val conflator: ValuesConfaltor<V>

    protected open val parser: ValuesParser = DefaultValuesParser

    override fun parseAnnotation(
        annotation: Annotation,
        arguments: A?
    ): S? {
        val tokens = tokenizer.tokenize(annotation.value)
        val values = tokens.mapNotNull { token -> parseValue(token, arguments) }
        val value = conflator.conflate(values) ?: return null
        return makeSpan(value)
    }

    /**
     * Specifies the way of parsing [token] into some value of type [V].
     */
    protected open fun parseValue(token: Token, arguments: A?): V? =
        arguments?.getValues()?.let { parser.parse(token, it) }

    /**
     * Obtains list of values, appropiate for type of this annotation processor.
     *
     * In order to use custom, extended arguments, one should perform type check.
     */
    protected abstract fun A.getValues(): QualifiedList<V>?

    /**
     * Creates new span of type [S], corresponding to type of this annotation processor.
     */
    protected abstract fun makeSpan(value: V): S?
}