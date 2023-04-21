package io.github.mmolosays.stringannotations.processor

import android.text.Annotation
import io.github.mmolosays.stringannotations.Logger
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.processor.parser.DefaultValuesParser
import io.github.mmolosays.stringannotations.processor.parser.Token
import io.github.mmolosays.stringannotations.processor.parser.ValuesParser

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
 * Abstract class for [AnnotationProcessor] implementations for single annotation type.
 * Utilizes a lot of usefull funtionality, making implementing custom [AnnotationProcessor] easier.
 *
 * @param V type of values for annotations of this type; e.g. `Int` for color.
 * @param A type of annotation arguments to obtain values from.
 * @param S type of spans to be produced.
 */
public abstract class AbstractAnnotationProcessor<V, A, S> : AnnotationProcessor<A, S> {

    protected open val parser: ValuesParser = DefaultValuesParser

    override fun parseAnnotation(
        annotation: Annotation,
        arguments: A?,
    ): S? =
        try {
            val token = Token(annotation.value)
            val value = parseValue(token, arguments)
            if (value != null) makeSpan(value) else null
        } catch (e: Exception) {
            Logger.logUnableToParse(annotation)
            null
        }

    /**
     * Specifies the way of parsing [token] into some value of type [V].
     */
    protected open fun parseValue(token: Token, arguments: A?): V? =
        arguments?.getValues()?.let { parser.parse(token, it) }

    /**
     * Obtains list of values, appropiate for type of this annotation processor.
     *
     * In order to use custom arguments, one should perform type check.
     */
    protected abstract fun A.getValues(): QualifiedList<V>?

    /**
     * Creates new span of type [S], corresponding to type of this annotation processor.
     */
    protected abstract fun makeSpan(value: V): S?
}