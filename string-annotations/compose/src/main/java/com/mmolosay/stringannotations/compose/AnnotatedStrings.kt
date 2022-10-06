package com.mmolosay.stringannotations.compose

import android.text.SpannedString
import androidx.compose.ui.text.AnnotatedString
import com.mmolosay.stringannotations.compose.internal.SpanProcessor
import com.mmolosay.stringannotations.internal.AnnotatedStringFormatter
import com.mmolosay.stringannotations.internal.AnnotationSpanProcessor
import com.mmolosay.stringannotations.internal.SpannedProcessor

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
        arguments: ComposeArguments? = null,
        vararg formatArgs: Any
    ): AnnotatedString {
        // 0. prepare dependencies
        val processor = StringAnnotations.dependencies.processor
        val annotations = SpannedProcessor.getAnnotationSpans(string)

        // 1. format, preserving annotation spans
        val spannable = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        // 2. parse annotation ranges
        val ranges = AnnotationSpanProcessor.parseAnnotationRanges(spannable, annotations)

        // 3. parse Annotation-s into spans of SpanStyle type
        val spans = annotations.map { annotation ->
            processor.parseAnnotation(annotation, arguments)
        }

        // 4. apply spans and return result AnnotatedString
        return SpanProcessor.applySpans(spannable, spans, ranges)
    }
}