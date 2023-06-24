@file:Suppress("FunctionName")

package io.github.mmolosays.stringannotations.processor

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.parser.CommonValueParser
import io.github.mmolosays.stringannotations.parser.ValueParser

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

/*
 * `AnnotationProcessor` extensions and utils.
 */

/**
 * Builder for [AnnotationProcessor] implementations for single annotation type.
 * Employs [AbstractAnnotationProcessor] inside.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method.
 */
public fun <V, A, S> AnnotationProcessor(
    parser: ValueParser = CommonValueParser,
    values: A.() -> QualifiedList<V>?,
    factory: (value: V) -> S?,
): AnnotationProcessor<A, S> =
    object : AbstractAnnotationProcessor<V, A, S>(parser) {

        override val A.values: QualifiedList<V>?
            get() = values()

        override fun makeSpan(value: V): S? =
            factory(value)
    }