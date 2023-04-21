package io.github.mmolosays.stringannotations.compose.internal

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import io.github.mmolosays.stringannotations.compose.args.Clickable
import io.github.mmolosays.stringannotations.compose.processor.ComposeSpan

/**
 * Processes [ComposeSpan]s.
 */
internal object SpanProcessor {

    const val ClickableTag = "StringAnnotations.clickable"

    /**
     * Attaches specified [spans] to the [ranges] of [text] accordingly.
     * All `null` spans will be skipped.
     */
    fun applySpans(
        text: CharSequence,
        spans: List<ComposeSpan?>,
        ranges: List<IntRange>,
    ): AnnotatedString {
        val builder = AnnotatedString.Builder(text.toString())
        for (i in spans.indices) {
            val span = spans.getOrNull(i) ?: continue
            val range = ranges.getOrNull(i) ?: continue
            applySpan(builder, span, range)
        }
        return builder.toAnnotatedString()
    }

    /**
     * Attaches specified [span] to the [range] of [builder] object.
     */
    private fun applySpan(
        builder: AnnotatedString.Builder,
        span: ComposeSpan,
        range: IntRange,
    ) {
        val (style, paragraph, clickable) = span
        when {
            style != null -> applySpan(builder, style, range)
            paragraph != null -> applySpan(builder, paragraph, range)
            clickable != null -> applySpan(builder, clickable, range)
        }
    }

    private fun applySpan(
        builder: AnnotatedString.Builder,
        span: SpanStyle,
        range: IntRange,
    ) {
        builder.addStyle(span, range.first, range.last)
    }

    private fun applySpan(
        builder: AnnotatedString.Builder,
        span: ParagraphStyle,
        range: IntRange,
    ) {
        builder.addStyle(span, range.first, range.last)
    }

    private fun applySpan(
        builder: AnnotatedString.Builder,
        span: Clickable,
        range: IntRange,
    ) {
        builder.addStringAnnotation(
            tag = ClickableTag,
            annotation = span.annotation,
            start = range.first,
            end = range.last
        )
    }
}