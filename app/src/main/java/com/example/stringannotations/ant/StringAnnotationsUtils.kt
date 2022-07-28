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

    fun formatAnnotatedString(
        context: Context,
        @StringRes stringRes: Int,
        vararg contents: AnnotationContent
    ): Spanned =
        formatAnnotatedString(
            resources = context.resources,
            stringRes = stringRes,
            contents = contents
        )

    fun formatAnnotatedString(
        resources: Resources,
        @StringRes stringRes: Int,
        vararg contents: AnnotationContent
    ): Spanned =
        formatAnnotatedString(
            string = resources.getText(stringRes) as SpannedString,
            contents = contents
        )

    // TODO: парсить аннотации от самой внутренней до самой внешней: решить задачу скобок
    private fun formatAnnotatedString(
        string: SpannedString,
        vararg contents: AnnotationContent
    ): Spanned {
        val annotations = getStringAnnotations(string)
        return SpannableStringBuilder(string).apply {
            annotations.forEachIndexed { i, annotation ->
                val strargs = contents.getOrNull(i)?.args ?: return@forEachIndexed
                val start = getSpanStart(annotation)
                val end = getSpanEnd(annotation)
                val annotated = substring(start, end)
                val formatted = String.format(annotated, strargs)
                replace(start, end, formatted)
            }
        }
    }

    private fun getStringAnnotations(string: SpannedString): Array<out Annotation> =
        string.getSpans()
}