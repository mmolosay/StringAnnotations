package com.mmolosay.stringannotations

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.internal.AnnotatedStringFormatter
import com.mmolosay.stringannotations.internal.AnnotationProcessor
import com.mmolosay.stringannotations.internal.AnnotationTreeBuilder
import com.mmolosay.stringannotations.internal.SpanProcessor
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

public object AnnotatedStrings {

    /**
     * One should prefer using higher level extension functions,
     * like [com.mmolosay.stringannotations.getAnnotatedString].
     *
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into spans.
     * 3. Applies spans to the [string].
     */
    public fun process(
        context: Context,
        string: SpannedString,
        valueArgs: ValueArgs? = null,
        vararg formatArgs: Any
    ): Spanned {
        // 0. prepare dependencies
        val resolver = StringAnnotations.dependencies.resolver
        val annotations = SpannedProcessor.getAnnotationSpans(string)
        val builder = SpannableStringBuilder(string)
        val stringArgs = stringifyFormatArgs(formatArgs)

        // 1. build annotation tree
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        // 2. replace wildcards, preserving annotation spans
        AnnotatedStringFormatter.format(builder, tree, stringArgs)

        // 3. parse updated ranges
        val ranges = AnnotationProcessor.parseAnnotationRanges(builder, annotations)

        // 4. parse Annotation-s into spans of CharacterStyle type
        val spans = annotations.mapNotNull { annotation ->
            resolver.resolve(annotation.key)?.parseAnnotation(context, annotation, valueArgs)
        }

        // 5. apply spans to string
        SpanProcessor.applySpans(builder, ranges, spans)

        return builder
    }

    /**
     * Version of [process] method, but receives [id] of string resource with annotations.
     */
    public fun process(
        context: Context,
        @StringRes id: Int,
        valueArgs: ValueArgs? = null,
        vararg formatArgs: Any
    ): Spanned =
        process(
            context = context,
            string = context.resources.getText(id) as SpannedString,
            valueArgs = valueArgs,
            formatArgs = formatArgs
        )

    /**
     * Maps [formatArgs] into array of [String]s by resolving their string values
     * (see [java.lang.String.valueOf]).
     */
    private fun stringifyFormatArgs(
        formatArgs: Array<out Any>
    ): Array<out String> =
        Array<String>(formatArgs.size) { i ->
            java.lang.String.valueOf(formatArgs[i])
        }
}