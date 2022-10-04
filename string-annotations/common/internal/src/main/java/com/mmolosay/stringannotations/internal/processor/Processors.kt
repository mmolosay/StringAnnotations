package com.mmolosay.stringannotations.internal.processor

import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
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

/*
 * AnnotationProcessor builders.
 */

/**
 * `AnnotationProcessor` for "size-absolute" annotation type.
 */
public fun <S> BaseAbsoluteSizeAnnotationProcessor(
    factory: (value: Int) -> S
): AnnotationProcessor<S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.Single(),
        values = { absSizes },
        factory = factory
    )