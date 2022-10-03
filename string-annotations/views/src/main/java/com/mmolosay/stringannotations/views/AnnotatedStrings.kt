package com.mmolosay.stringannotations.views

import android.content.Context
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.internal.AnnotatedStringFormatter
import com.mmolosay.stringannotations.internal.AnnotationSpanProcessor
import com.mmolosay.stringannotations.internal.SpannedProcessor
import com.mmolosay.stringannotations.views.internal.SpanProcessor

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
 * Processes annotated string for Android Views system.
 */
public object AnnotatedStrings {

    /**
     * One should prefer using higher level extension functions.
     *
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into spans.
     * 3. Applies spans to the [string].
     */
    public fun process(
        string: SpannedString,
        arguments: Arguments? = null,
        vararg formatArgs: Any
    ): Spanned {
        // 0. prepare dependencies
        val processor = StringAnnotations.dependencies.processor
        val annotations = SpannedProcessor.getAnnotationSpans(string)

        // 1. format, preserving annotation spans
        val spannable = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        // 2. parse annotation ranges
        val ranges = AnnotationSpanProcessor.parseAnnotationRanges(spannable, annotations)

        // 3. parse Annotation-s into spans of CharacterStyle type
        val spans = annotations.mapNotNull { annotation ->
            processor.parseAnnotation(annotation, arguments)
        }

        // 4. apply spans to string
        SpanProcessor.applySpans(spannable, ranges, spans)

        return spannable
    }

    /**
     * Version of [process] method, but receives [id] of string resource with annotations.
     */
    public fun process(
        context: Context,
        @StringRes id: Int,
        arguments: Arguments? = null,
        vararg formatArgs: Any
    ): Spanned =
        process(
            string = context.resources.getText(id) as SpannedString,
            arguments = arguments,
            formatArgs = formatArgs
        )
}