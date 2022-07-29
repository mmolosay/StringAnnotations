package com.example.stringannotations

import android.content.Context
import android.content.res.Resources
import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.example.stringannotations.tree.AnnotationNode

object AnnotatedStrings {

    fun format(
        context: Context,
        @StringRes stringRes: Int,
        vararg args: String
    ): Spanned =
        format(
            resources = context.resources,
            stringRes = stringRes,
            args = args
        )

    fun format(
        resources: Resources,
        @StringRes stringRes: Int,
        vararg args: String
    ): Spanned =
        format(
            string = resources.getText(stringRes) as SpannedString,
            args = args
        )

    /**
     *
     * Case 1:
     * Input: "first: %s, <annotation>second: %s</annotation>, third: <annotation>%s</annotation>"
     * Output: "first: red, second: green, third: blue"
     * Arguments: ["red", "green", "blue"]
     * Ranges In: [{11, 21}, {30, 32}]
     * Ranges Out: [{11, 21}, {30, 32}]
     *
     * Case 2:
     * Input: "abc <defgh <<i> <jk>>> lmno <pqr> st"
     * Ranges: [{4, 14}, {10, 14}, {10, 11}, {12, 14}, {20, 23}]
     */
    private fun format(
        string: SpannedString,
        vararg args: String
    ): Spanned {
        val tree = StringAnnotationProcessor.buildAnnotationTree(string)
        println() // TODO: remove
//        val formatted = SpannableStringBuilder(string).apply {
//            outer.forEach { annotation ->
//                val start = getSpanStart(annotation)
//                val end = getSpanEnd(annotation)
//                val formatted = String.format(anno)
//            }
//        }
//        annotations.forEachIndexed { i, annotation ->
//            val strargs = args.getOrNull(i)?.args ?: return@forEachIndexed
//            val start = builder.getSpanStart(annotation)
//            val end = builder.getSpanEnd(annotation)
//            val annotated = substring(start, end)
//            val formatted = String.format(annotated, strargs)
//            replace(start, end, formatted)
//        }
//        return builder
        return string
    }
}