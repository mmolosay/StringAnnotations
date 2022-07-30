package com.example.stringannotations.processors

import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import com.example.stringannotations.AnnotationType

/**
 * Processes [CharacterStyle]s.
 */
internal object CharacterStyleProcessor {

    fun makeCharacterStyles(types: List<AnnotationType>): List<CharacterStyle?> =
        types.map { type -> makeCharacterStyle(type) }

    fun makeCharacterStyle(type: AnnotationType): CharacterStyle? =
        when (type) {
            is AnnotationType.Background -> BackgroundColorSpan(type.color)
            is AnnotationType.Color -> ForegroundColorSpan(type.color)
            is AnnotationType.Combined -> TODO()
            AnnotationType.Unknown -> null
        }
}