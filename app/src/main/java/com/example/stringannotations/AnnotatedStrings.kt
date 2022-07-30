package com.example.stringannotations

import android.content.Context
import android.content.res.Resources
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.example.stringannotations.processors.AnnotatedStringProcessor
import com.example.stringannotations.processors.AnnotationNodeProcessor
import com.example.stringannotations.processors.AnnotationProcessor
import com.example.stringannotations.tree.AnnotationNode
import com.example.stringannotations.tree.AnnotationTreeBuilder

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
            resources = resources,
            args = args
        )

    private fun format(
        string: SpannedString,
        resources: Resources,
        vararg args: String
    ): Spanned {
        val annotations = AnnotationProcessor.getAnnotationSpans(string)
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)
        val builder = SpannableStringBuilder(string)
        AnnotatedStringProcessor.format(builder, tree, *args)
        return builder
    }
}