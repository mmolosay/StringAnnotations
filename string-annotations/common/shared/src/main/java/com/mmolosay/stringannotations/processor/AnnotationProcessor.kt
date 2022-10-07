package com.mmolosay.stringannotations.processor

import android.text.Annotation
import com.mmolosay.stringannotations.args.Arguments

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
 * Parses [Annotation] of some [android.text.Spanned] string into span of [S] type.
 *
 * It can be used both as processor for single annotation type or for all of them.
 */
public fun interface AnnotationProcessor<S> {

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
        arguments: Arguments?
    ): S?
}