package com.mmolosay.stringannotations.compose.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

/**
 * Processes spans (as [SpanStyle]).
 */
internal object SpanProcessor {

    /**
     * Attaches specified [spans] to the [ranges] of [text] accordingly.
     * All `null` spans will be skipped.
     */
    fun applySpans(
        text: CharSequence,
        spans: List<SpanStyle?>,
        ranges: List<IntRange>
    ): AnnotatedString {
        val builder = AnnotatedString.Builder(text.toString())
        for (i in spans.indices) {
            val span = spans.getOrNull(i) ?: return builder.toAnnotatedString()
            val range = ranges.getOrNull(i) ?: return builder.toAnnotatedString()
            applySpan(builder, span, range)
        }
        return builder.toAnnotatedString()
    }

    /**
     * Attaches specified [span] to the [range] of [builder] object.
     */
    private fun applySpan(
        builder: AnnotatedString.Builder,
        span: SpanStyle,
        range: IntRange
    ) {
        builder.addStyle(span, range.first, range.last)
    }
}