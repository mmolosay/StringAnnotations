package com.example.stringannotations.processors

import android.text.Spannable
import android.text.style.CharacterStyle
import com.example.stringannotations.PlacedCharacterStyle
import com.example.stringannotations.StringAnnotation

/**
 * Processes [PlacedCharacterStyle]s.
 */
internal object PlacedCharacterStyleProcessor {

    fun applyCharacterStyles(
        spannable: Spannable,
        annotations: List<StringAnnotation>,
        styles: List<CharacterStyle?>
    ) {
        parsePlacedCharacterStyles(annotations, styles).forEach { placed ->
            applyCharacterStyle(spannable, placed)
        }
    }

    fun parsePlacedCharacterStyles(
        annotations: List<StringAnnotation>,
        styles: List<CharacterStyle?>
    ): List<PlacedCharacterStyle> =
        styles.mapIndexedNotNull { i, style ->
            style ?: return@mapIndexedNotNull null
            val annotation = annotations[i]
            PlacedCharacterStyle(style, annotation.start, annotation.end)
        }

    private fun applyCharacterStyle(
        spannable: Spannable,
        placed: PlacedCharacterStyle
    ) {
        spannable.setSpan(
            placed.style,
            placed.start,
            placed.end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}