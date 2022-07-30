package com.example.stringannotations.processor

import android.text.ParcelableSpan
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.example.stringannotations.AnnotationType
import com.example.stringannotations.StringAnnotation

/**
 * Processes spans (as [CharacterStyle]).
 */
internal object SpanProcessor {

    /**
     * Cretes spans for specified [types] and applies them to the [spannable].
     */
    fun applyAnnotationTypes(
        spannable: Spannable,
        annotations: List<StringAnnotation>,
        types: List<AnnotationType>
    ) {
        makeSpans(types)
            .forEachIndexed action@{ i, span ->
                span ?: return@action // skip nulls
                val annotation = annotations[i]
                applySpan(spannable, span, annotation.start, annotation.end)
            }
    }

    /**
     * Instantiates new [ParcelableSpan]s for specified [types].
     *
     * @see makeSpan
     */
    private fun makeSpans(types: List<AnnotationType>): List<CharacterStyle?> =
        types.map { type -> makeSpan(type) }

    /**
     * Instantiates new [ParcelableSpan] for specified [type].
     *
     * @return new span or `null`, if there is no span for the [type].
     */
    private fun makeSpan(type: AnnotationType): CharacterStyle? =
        when (type) {
            is AnnotationType.Background -> BackgroundColorSpan(type.color)
            is AnnotationType.Foreground -> ForegroundColorSpan(type.color)
            is AnnotationType.TypefaceStyle -> StyleSpan(type.style)
            is AnnotationType.UnderlineStyle -> UnderlineSpan()
            AnnotationType.Unknown -> null
        }

    private fun applySpan(
        spannable: Spannable,
        span: CharacterStyle,
        start: Int,
        end: Int
    ) {
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}