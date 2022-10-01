package com.mmolosay.stringannotations.view

import android.content.Context
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.mmolosay.stringannotations.view.processor.AnnotationProcessor
import com.mmolosay.stringannotations.internal.AnnotatedStringFormatter
import com.mmolosay.stringannotations.internal.AnnotationSpanProcessor
import com.mmolosay.stringannotations.internal.SpannedProcessor
import com.mmolosay.stringannotations.view.internal.SpanProcessor

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

public object AnnotatedStrings {

    /**
     * One should prefer using higher level extension functions,
     * like [com.mmolosay.stringannotations.getAnnotatedString].
     *
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into spans.
     * 3. Applies spans to the [string].
     *
     * @throws IllegalArgumentException if [StringAnnotations.Dependencies.processor], obtained
     * from [StringAnnotations.dependencies] cannot be casted to [AnnotationProcessor]<[A]>.
     */
    public fun <A> process(
        context: Context,
        string: SpannedString,
        arguments: A? = null,
        vararg formatArgs: Any
    ): Spanned {
        // 0. prepare dependencies
        val processor = requireAnnotationProcessor<A>()
        val annotations = SpannedProcessor.getAnnotationSpans(string)

        // 1. format, preserving annotation spans
        val spannable = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        // 3. parse annotation ranges
        val ranges = AnnotationSpanProcessor.parseAnnotationRanges(spannable, annotations)

        // 4. parse Annotation-s into spans of CharacterStyle type
        val spans = annotations.mapNotNull { annotation ->
            processor.parseAnnotation(context, annotation, arguments)
        }

        // 5. apply spans to string
        SpanProcessor.applySpans(spannable, ranges, spans)

        return spannable
    }

    /**
     * Version of [process] method, but receives [id] of string resource with annotations.
     */
    public fun <A> process(
        context: Context,
        @StringRes id: Int,
        arguments: A? = null,
        vararg formatArgs: Any
    ): Spanned =
        process(
            context = context,
            string = context.resources.getText(id) as SpannedString,
            arguments = arguments,
            formatArgs = formatArgs
        )

    /*
     * Bottle neck — you either pass AnnotationProcessor with known generic type
     * as function parameter yourself, or save it in dependencies, erasing its generic's type.
     *
     * In first case, you lose convenience, being forced to provide AnnotationProcessor
     * instance each time using 'process()' function.
     *
     * In second case, you're forced to do unsafe cast as done below.
     *
     * I was not able to find elegant solution for this problem.
     */
    @Suppress("UNCHECKED_CAST")
    private fun <A> requireAnnotationProcessor(): AnnotationProcessor<A> =
        StringAnnotations.dependencies.processor as? AnnotationProcessor<A>
            ?: throw IllegalArgumentException(
                "StringAnnotations was configured to work with different type of Annotation Arguments"
            )
}