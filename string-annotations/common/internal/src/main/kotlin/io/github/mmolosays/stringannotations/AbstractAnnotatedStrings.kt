package io.github.mmolosays.stringannotations

import android.text.Spannable
import android.text.SpannedString
import io.github.mmolosays.stringannotations.AnnotationSpanProcessor.rangeOf
import io.github.mmolosays.stringannotations.args.Arguments
import io.github.mmolosays.stringannotations.processor.AnnotationProcessor
import io.github.mmolosays.stringannotations.tree.AnnotationTreeBuilder

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
 * Processes annotated strings.
 *
 * @param A annotation arguments for this string.
 * @param S spans to be produced on parsing annotaions.
 * @param P annotation processor to be used to parse annotations into spans.
 * @param R result of processing, some kind of string representation with applied spans.
 */
public abstract class AbstractAnnotatedStrings<A : Arguments<*>, S, P : AnnotationProcessor<A, S>, R> {

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
        arguments: A? = null,
        vararg formatArgs: Any,
    ): R {
        // 0. prepare dependencies
        val processor = getAnnotationProcessor()
        val annotations = SpannedProcessor.getAnnotationSpans(string)
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        // 1. format, preserving annotation spans
        val spannable = AnnotatedStringFormatter.format(string, tree, *formatArgs)

        // 2. parse annotation ranges (may have changed after formatting)
        val ranges = annotations.map { annotation ->
            spannable rangeOf annotation
        }

        // 3. parse Annotation-s into spans of SpanStyle type
        val spans = annotations.map { annotation ->
            processor.parseAnnotation(annotation, arguments)
        }

        // 4. apply spans and return result AnnotatedString
        return applySpans(spannable, spans, ranges)
    }

    /**
     * Retrieves [AnnotationProcessor] to be used for parsing annotations.
     * Normally it should obtain an annotation processor set during the stage of library initialization.
     */
    protected abstract fun getAnnotationProcessor(): P

    /**
     * Applies [spans] at corresponsing [ranges] to [spannable].
     */
    protected abstract fun applySpans(
        spannable: Spannable,
        spans: List<S?>,
        ranges: List<IntRange>,
    ): R
}