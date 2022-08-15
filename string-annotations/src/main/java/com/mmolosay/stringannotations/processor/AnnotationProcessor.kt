package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.ValueArgs

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
 * Parses [Annotation] of some [android.text.Spanned] string into span of [CharacterStyle] type.
 */
public sealed interface AnnotationProcessor {

    /**
     * Parses specified [annotation] into span of [CharacterStyle] type.
     *
     * @param context caller context.
     * @param annotation annotation to be parsed.
     * @param args annotation runtime arguments to be substituted instead of placeholders.
     *
     * @return parsed span of [CharacterStyle] type, or `null`, if annotation is unsupported or
     * unparseable.
     */
    public fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: ValueArgs
    ): CharacterStyle?
}