package com.example.stringannotations

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import android.text.style.ClickableSpan
import androidx.annotation.StringRes
import com.example.stringannotations.lib.StringAnnotations
import com.example.stringannotations.mapper.AnnotationMapper
import com.example.stringannotations.processor.AnnotatedStringProcessor
import com.example.stringannotations.processor.SpanProcessor
import com.example.stringannotations.processor.SpannedProcessor
import com.example.stringannotations.tree.AnnotationTreeBuilder

internal object AnnotatedStrings {

    /**
     * One should prefer using higher level extension functions, like [Context.getAnnotatedString].
     *
     * Before calling the method, make sure, that [StringAnnotations] is configured.
     * Otherwise method will throw [IllegalStateException].
     *
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into [AnnotationType] instances.
     * 3. Applies spans, corresponding to parsed types to the [string].
     *
     * @throws IllegalStateException if [StringAnnotations] has not been configured.
     */
    fun process(
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

        // 3. parse updated StringAnnotations
        val strAnnotations = AnnotationMapper.parseStringAnnotations(builder, annotations)

        // 4. parse Annotation-s into AnnotationType-s
        val types =
            AnnotationMapper.parseAnnotationTypes(context, processor, annotations, clickables)

        // 5. apply AnnotationType-s as corresponding spans.
        SpanProcessor.applyAnnotationTypes(builder, strAnnotations, types)

        return builder
    }

    /**
     * Version of [process] method, but receives [id] of string resource with annotations.
     */
    fun process(
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