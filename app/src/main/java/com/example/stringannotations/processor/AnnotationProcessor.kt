package com.example.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.ClickableSpan
import com.example.stringannotations.AnnotationType

/**
 * Parses [Annotation] of some [android.text.Spanned] string into [AnnotationType].
 */
sealed interface AnnotationProcessor {

    /**
     * Parses specified [annotation] into [AnnotationType].
     *
     * @param context caller context.
     * @param annotation annotation to be parsed.
     * @param clickables list of [ClickableSpan], that will be used for [AnnotationType.Clickable] types.
     */
    fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        clickables: List<ClickableSpan>
    ): AnnotationType
}