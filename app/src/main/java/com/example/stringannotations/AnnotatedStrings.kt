package com.example.stringannotations

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import com.example.stringannotations.processors.AnnotatedStringProcessor
import com.example.stringannotations.processors.AnnotationProcessor
import com.example.stringannotations.processors.AnnotationTypeProcessor
import com.example.stringannotations.processors.CharacterStyleProcessor
import com.example.stringannotations.processors.PlacedCharacterStyleProcessor
import com.example.stringannotations.processors.StringAnnotationProcessor
import com.example.stringannotations.tree.AnnotationTreeBuilder

object AnnotatedStrings {

    fun format(
        context: Context,
        @StringRes stringRes: Int,
        vararg args: String
    ): Spanned =
        format(
            context = context,
            string = context.resources.getText(stringRes) as SpannedString,
            args = args
        )

    fun format(
        context: Context,
        string: SpannedString,
        vararg args: String
    ): Spanned {
        val annotations = AnnotationProcessor.getAnnotationSpans(string)
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)
        val builder = SpannableStringBuilder(string)

        // 1. replace wildcards, preserving annotation spans
        AnnotatedStringProcessor.format(builder, tree, *args)

        // 2. parse updated StringAnnotations
        val strAnnotations = StringAnnotationProcessor.parseStringAnnotations(builder, annotations)

        // 2. parse AnnotationType-s
        val types = AnnotationTypeProcessor.parseAnnotationTypes(context, annotations)

        // 3. make CharacterStyle-s
        val styles = CharacterStyleProcessor.makeCharacterStyles(types)

        // 4. apply CharacterStyle-s
        PlacedCharacterStyleProcessor.applyCharacterStyles(builder, strAnnotations, styles)

        return builder
    }
}