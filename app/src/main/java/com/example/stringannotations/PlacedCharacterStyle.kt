package com.example.stringannotations

import android.text.style.CharacterStyle

/**
 * [CharacterStyle], which should be placed at position from [start] to [end].
 */
internal data class PlacedCharacterStyle(
    val style: CharacterStyle,
    val start: Int,
    val end: Int
)