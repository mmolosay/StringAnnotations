package com.example.stringannotations.ant

import android.content.Context
import android.content.res.Resources
import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.core.text.getSpans

object StringAnnotationsUtils {

    // TODO: investigate PlacableSpan parent of Annotation and try build custom annotations with it

    fun formatAnnotatedString(
        context: Context,
        @StringRes stringRes: Int,
        vararg args: Array<Any>
    ): Spanned =
        formatAnnotatedString(
            resources = context.resources,
            stringRes = stringRes,
            args = args
        )

    fun formatAnnotatedString(
        resources: Resources,
        @StringRes stringRes: Int,
        vararg args: Array<Any>
    ): Spanned =
        formatAnnotatedString(
            string = resources.getText(stringRes) as SpannedString,
            args = args
        )

    private fun formatAnnotatedString(
        string: SpannedString,
        vararg args: Array<Any>
    ): Spanned {
        val annotations = getStringAnnotations(string)
        return SpannableStringBuilder(string).apply {
            annotations.forEachIndexed { i, annotation ->
                val start = getSpanStart(annotation)
                val end = getSpanEnd(annotation)
                val annotated = substring(start, end)
                val strargs = getArgsStringValues(args[i])
                val formatted = String.format(annotated, *strargs)
                replace(start, end, formatted)
            }
        }
    }

    private fun getStringAnnotations(string: SpannedString): Array<out Annotation> =
        string.getSpans()

    private fun getArgsStringValues(args: Array<Any>): Array<String> =
         Array(args.size) { i ->
            java.lang.String.valueOf(args[i])
        }
}