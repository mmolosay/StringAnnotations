package io.github.mmolosays.stringannotations.views.clickable

import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.util.TypedValue
import io.github.mmolosays.stringannotations.views.R
import io.github.mmolosays.stringannotations.views.args.Clickable

/**
 * Retrieves [Clickable.Appearance] from specified [theme].
 *
 * First, it will try to resolve style, set as attribute [R.attr.clickableTextAppearance] value
 * in the theme.
 * If it is `null`, then will use default [R.style.Base_ClickableTextAppearance] style.
 */
public fun Clickable.Appearance.Companion.from(theme: Resources.Theme): Clickable.Appearance =
    theme.getClickableSpanAppearance()

private fun Resources.Theme.getClickableSpanAppearance(): Clickable.Appearance {
    val value = TypedValue()
    val resolved = this.resolveAttribute(R.attr.clickableTextAppearance, value, true)
    val styleRes = if (resolved) value.data else R.style.Base_ClickableTextAppearance
    val typed = this.obtainStyledAttributes(styleRes, R.styleable.ClickableTextAppearance)
    return makeClickableSpanAppearance(typed)
}

private fun makeClickableSpanAppearance(typed: TypedArray) =
    Clickable.Appearance(
        underlineText = typed.getBoolean(
            R.styleable.ClickableTextAppearance_underlineText,
            false,
        ),
        textColor = typed.getColor(
            R.styleable.ClickableTextAppearance_android_textColor,
            Color.BLUE,
        ),
    )