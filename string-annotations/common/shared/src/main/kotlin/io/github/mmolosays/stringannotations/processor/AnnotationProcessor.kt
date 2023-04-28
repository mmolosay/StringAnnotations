package io.github.mmolosays.stringannotations.processor

import android.text.Annotation

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
 * Centerpiece of `StringAnnotations` library.
 *
 * `AnnotationProcessor` parses [Annotation] of some annotated string into span of [S] type.
 * Values for spans will be obtained from annotation arguments of type [A].
 *
 * It can be used either as a processor for a single annotation type or for all of them.
 */
public fun interface AnnotationProcessor<A, S> {

    /**
     * Parses specified [annotation] into span of [S] type.
     *
     * @param annotation annotation to be parsed.
     * @param arguments annotation arguments to be substituted instead of placeholders.
     *
     * @return span of [S] type, or `null`, if annotation can not be parsed.
     */
    public fun parseAnnotation(
        annotation: Annotation,
        arguments: A?,
    ): S?
}