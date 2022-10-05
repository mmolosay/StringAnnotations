package com.mmolosay.stringannotations.views.args

import android.graphics.Color
import androidx.annotation.ColorInt
import com.mmolosay.stringannotations.spans.clickable.ClickOwner

/**
 * Implementation of [ClickOwner] for Android Views system.
 *
 * Additionaly, provides [appearance] of clickable text.
 */
public data class Clickable(
    public val appearance: Appearance = Appearance(),
    override val action: ClickOwner.ClickAction
) : ClickOwner {

    /**
     * Appearance properties of clickable text.
     */
    public data class Appearance(

        /**
         * Defines, whether text should be underlined or not.
         */
        val underlineText: Boolean = false,

        /**
         * Color of text.
         */
        @ColorInt val textColor: Int = Color.BLUE
    ) {
        public companion object
    }
}