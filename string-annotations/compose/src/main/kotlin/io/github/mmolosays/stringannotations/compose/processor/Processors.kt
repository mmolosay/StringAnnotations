package io.github.mmolosays.stringannotations.compose.processor

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.compose.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.ComposeArguments
import io.github.mmolosays.stringannotations.processor.AnnotationProcessor
import io.github.mmolosays.stringannotations.processor.parser.ValueParser

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
 * Builder for [AnnotationProcessor] implementations for single annotation type for Compose UI.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method.
 */
public fun <V> ComposeAnnotationProcessor(
    parser: ValueParser,
    values: ComposeArguments.() -> QualifiedList<V>?,
    factory: (value: V) -> ComposeSpan?,
): ComposeAnnotationProcessor =
    AnnotationProcessor(
        parser = parser,
        values = values,
        factory = factory,
    )