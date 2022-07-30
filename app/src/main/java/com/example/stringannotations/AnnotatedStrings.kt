package com.example.stringannotations

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.example.stringannotations.processor.AnnotatedStringProcessor
import com.example.stringannotations.processor.AnnotationProcessor
import com.example.stringannotations.processor.AnnotationTypeProcessor
import com.example.stringannotations.processor.SpanProcessor
import com.example.stringannotations.processor.StringAnnotationProcessor
import com.example.stringannotations.tree.AnnotationTreeBuilder

object AnnotatedStrings {

    /**
     * 1. Formats specified [string] with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans.
     * 2. Parses `<annotation>`s into actual spans (see [AnnotationType]).
     * 3. Applies parsed spans to the [string].
     */
    fun format(
        context: Context,
        string: SpannedString,
        vararg formatArgs: Any
    ): Spanned {
        val annotations = AnnotationProcessor.getAnnotationSpans(string)
        val builder = SpannableStringBuilder(string)
        val stringArgs = stringifyFormatArgs(formatArgs)

        // 1. build annotation tree
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        // 2. replace wildcards, preserving annotation spans
        AnnotatedStringProcessor.format(builder, tree, *stringArgs)

        // 3. parse updated StringAnnotations
        val strAnnotations = StringAnnotationProcessor.parseStringAnnotations(builder, annotations)

        // 4. parse AnnotationType-s
        val types = AnnotationTypeProcessor.parseAnnotationTypes(context, annotations)

        // 5. apply AnnotationType-s
        SpanProcessor.applyAnnotationTypes(builder, strAnnotations, types)

        return builder
    }

    /**
     * Sophisticated version of [AnnotatedStrings.format].
     */
    fun format(
        context: Context,
        @StringRes id: Int,
        vararg formatArgs: Any
    ): Spanned =
        format(
            context = context,
            string = context.resources.getText(id) as SpannedString,
            formatArgs = formatArgs
        )

    private fun stringifyFormatArgs(
        formatArgs: Array<out Any>
    ): Array<out String> =
        Array<String>(formatArgs.size) { i ->
            java.lang.String.valueOf(formatArgs[i])
        }
}