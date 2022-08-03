package com.mmolosay.stringannotations.processor

import android.text.Spannable
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.StringAnnotation

/**
 * Processes spans (as [CharacterStyle]).
 */
internal object SpanProcessor {

    /**
     * Attach specified [spans] to the ranges of corresponding [annotations] of [spannable] object.
     * All `null` spans will be skipped.
     */
    fun applySpans(
        spannable: Spannable,
        annotations: List<StringAnnotation>,
        spans: List<CharacterStyle?>
    ) {
        spans
            .forEachIndexed action@{ i, span ->
                span ?: return@action // skip nulls
                val annotation = annotations[i]
                applySpan(spannable, span, annotation.start, annotation.end)
            }
    }

    /**
     * Attaches specified [span] to the range from [start] to [end] of [spannable] object.
     */
    private fun applySpan(
        spannable: Spannable,
        span: CharacterStyle,
        start: Int,
        end: Int
    ) {
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}