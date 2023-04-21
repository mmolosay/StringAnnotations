package io.github.mmolosays.stringannotations.views.args

import android.graphics.Color
import androidx.annotation.ColorInt
import io.github.mmolosays.stringannotations.args.types.ClickOwner

/**
 * Implementation of [ClickOwner] for Android Views UI.
 *
 * Additionaly, provides [appearance] of clickable text.
 */
public data class Clickable(
    public val appearance: Appearance = Appearance(),
    override val action: ClickOwner.ClickAction,
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