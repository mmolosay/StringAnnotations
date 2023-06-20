package io.github.mmolosays.stringannotations

import io.github.mmolosays.stringannotations.args.Arguments
import io.github.mmolosays.stringannotations.args.types.ClickOwner
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize
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
 * Typealias for [Arguments], when generic types don't matter.
 */
private typealias AnyArguments = Arguments<*>

/**
 * `AnnotationProcessor` for `clickable` annotation type.
 */
public fun <C : ClickOwner, A : Arguments<C>, S> BaseClickableAnnotationProcessor(
    parser: ValueParser,
    factory: (value: C) -> S?,
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        parser = parser,
        values = { clickables },
        factory = factory,
    )

/**
 * `AnnotationProcessor` for any color annotation type.
 */
public fun <A : AnyArguments, S> BaseColorAnnotationProcessor(
    parser: ValueParser,
    factory: (value: Int) -> S?,
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        parser = parser,
        values = { colors },
        factory = factory,
    )

/**
 * `AnnotationProcessor` for `decoration` annotation type.
 */
public fun <A : AnyArguments, S> BaseDecorationAnnotationProcessor(
    parser: ValueParser,
    factory: (value: TextDecoration) -> S?,
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        parser = parser,
        values = { decorations },
        factory = factory,
    )

/**
 * `AnnotationProcessor` for `size` annotation type.
 */
public fun <A : AnyArguments, S> BaseSizeAnnotationProcessor(
    parser: ValueParser,
    factory: (value: TextSize) -> S?,
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        parser = parser,
        values = { sizes },
        factory = factory,
    )

/**
 * `AnnotationProcessor` for `style` annotation type.
 */
public fun <A : AnyArguments, S> BaseStyleAnnotationProcessor(
    parser: ValueParser,
    factory: (value: Int) -> S?,
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        parser = parser,
        values = { styles },
        factory = factory,
    )
