package com.mmolosay.stringannotations.core

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import android.text.style.ClickableSpan
import androidx.annotation.StringRes
import com.mmolosay.stringannotations.mapper.AnnotationMapper
import com.mmolosay.stringannotations.processor.AnnotatedStringProcessor
import com.mmolosay.stringannotations.processor.SpanProcessor
import com.mmolosay.stringannotations.processor.SpannedProcessor
import com.mmolosay.stringannotations.tree.AnnotationTreeBuilder

public object AnnotatedStrings {

    /**
     * One should prefer using higher level extension functions,
     * like [com.mmolosay.stringannotations.getAnnotatedString].
     *
     * Before calling the method, make sure, that [StringAnnotations] is configured.
     * Otherwise method will throw [IllegalStateException].
     *
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into spans.
     * 3. Applies spans to the [string].
     *
     * @throws IllegalStateException if [StringAnnotations] has not been configured.
     */
    public fun process(
        context: Context,
        string: SpannedString,
        clickables: List<ClickableSpan>,
        vararg formatArgs: Any
    ): Spanned {
        // 0. prepare dependencies
        val processor = StringAnnotations.requireAnnotaitonProcessor()
        val annotations = SpannedProcessor.getAnnotationSpans(string)
        val builder = SpannableStringBuilder(string)
        val stringArgs = stringifyFormatArgs(formatArgs)

        // 1. build annotation tree
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        // 2. replace wildcards, preserving annotation spans
        AnnotatedStringProcessor.format(builder, tree, *stringArgs)

        // 3. parse updated StringAnnotation-s
        val strAnnotations = AnnotationMapper.parseStringAnnotations(builder, annotations)

        // 4. parse Annotation-s into spans of CharacterStyle type
        val types = AnnotationMapper.parseAnnotations(context, processor, annotations, clickables)

        // 5. apply spans to string
        SpanProcessor.applySpans(builder, strAnnotations, types)

        return builder
    }

    /**
     * Version of [process] method, but receives [id] of string resource with annotations.
     */
    public fun process(
        context: Context,
        @StringRes id: Int,
        clickables: List<ClickableSpan>,
        vararg formatArgs: Any
    ): Spanned =
        process(
            context = context,
            string = context.resources.getText(id) as SpannedString,
            clickables = clickables,
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