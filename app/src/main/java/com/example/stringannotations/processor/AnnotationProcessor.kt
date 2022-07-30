package com.example.stringannotations.processor

import android.text.Annotation
import android.text.Spanned
import androidx.core.text.getSpans

/**
 * Processes [Annotation] spans.
 */
internal object AnnotationProcessor {

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     */
    fun getAnnotationSpans(string: Spanned): Array<out Annotation> =
        string.getSpans()

    /**
     * Retrieves [annotation]'s start and end positions in terms of specified [string].
     * If [annotation] is `null`, then we assume that is a top-most root, and return full [string]
     * range.
     */
    fun getAnnotationRange(
        string: Spanned,
        annotation: Annotation?
    ): IntRange {
        annotation ?: return 0..string.length
        val start = string.getSpanStart(annotation)
        val end = string.getSpanEnd(annotation)
        return start..end
    }
}