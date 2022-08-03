package com.mmolosay.stringannotations.lib

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Appearance properties of clickable text.
 */
data class ClickableTextAppearance(

    /**
     * Defines whether text should be underlined or not.
     */
    val underlineText: Boolean = false,

    /**
     * Color of text.
     */
    @ColorInt val textColor: Int = Color.BLUE
)