package io.github.mmolosays.stringannotations.processor

import android.text.Annotation
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.processor.parser.DefaultValueParser
import io.github.mmolosays.stringannotations.processor.parser.ValueParser
import io.github.mmolosays.stringannotations.utils.Logger

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

    protected open val parser: ValueParser = DefaultValueParser

    override fun parseAnnotation(
        annotation: Annotation,
        arguments: A?,
    ): S? =
        try {
            val value = parseValue(annotation.value, arguments)
            if (value != null) makeSpan(value) else null
        } catch (e: Exception) {
            Logger.logUnableToParse(annotation)
            null
        }

    /**
     * Specifies the way of parsing [placeholder] into some value of type [V].
     */
    protected open fun parseValue(placeholder: String, arguments: A?): V? =
        arguments?.values?.let { parser.parse(placeholder, it) }

    /**
     * Obtains list of values, appropiate for type of this annotation processor.
     *
     * In order to use custom arguments, one should perform type check.
     */
    protected abstract val A.values: QualifiedList<V>?

    /**
     * Creates new span of type [S], corresponding to type of this annotation processor.
     */
    protected abstract fun makeSpan(value: V): S?
}