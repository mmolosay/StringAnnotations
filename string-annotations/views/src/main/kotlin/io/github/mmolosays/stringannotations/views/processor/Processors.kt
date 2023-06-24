package io.github.mmolosays.stringannotations.views.processor

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.parser.ValueParser
import io.github.mmolosays.stringannotations.processor.AnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsArguments
import io.github.mmolosays.stringannotations.views.ViewsSpan

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
 * `AnnotationProcessor` builders.
 */

/**
 * Builder for [AnnotationProcessor] implementations for single annotation type for Android Views UI.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method.
 */
public fun <V> ViewsAnnotationProcessor(
    parser: ValueParser,
    values: ViewsArguments.() -> QualifiedList<V>?,
    factory: (value: V) -> ViewsSpan?,
): ViewsAnnotationProcessor =
    AnnotationProcessor(
        parser = parser,
        values = values,
        factory = factory,
    )