package com.mmolosay.stringannotations.spans

import android.content.Context
import android.content.res.Resources
import android.text.style.ClickableSpan
import android.view.View
import com.mmolosay.stringannotations.ClickableTextAppearance
import com.mmolosay.stringannotations.utils.ThemeUtils.getClickableTextAppearance

/*
 * Spans builder functions.
 */

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action and appearance.
 *
 * Appearance [builder] will be scoped to [ClickableTextAppearance], set in your [theme].
 */
public fun ClickableSpan(
    theme: Resources.Theme,
    builder: ClickableTextAppearance.() -> ClickableTextAppearance,
    onClick: (widget: View) -> Unit
): ClickableSpan {
    val themeAppearance = getThemeClickableTextAppearance(theme)
    val appearance = builder(themeAppearance)
    return ClickableSpan(
        appearance = appearance,
        onClick = onClick
    )
}

/**
 * More sophisticated version, which pulls theme out of [context].
 *
 * @see [com.mmolosay.stringannotations.spans.ClickableSpan]
 */
public fun ClickableSpan(
    context: Context,
    builder: ClickableTextAppearance.() -> ClickableTextAppearance,
    onClick: (widget: View) -> Unit
): ClickableSpan {
    val themeAppearance = getThemeClickableTextAppearance(context.theme)
    val appearance = builder(themeAppearance)
    return ClickableSpan(
        appearance = appearance,
        onClick = onClick
    )
}

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action.
 * The [appearance]'s default value will be retrieved from [theme].
 */
public fun ClickableSpan(
    theme: Resources.Theme,
    appearance: ClickableTextAppearance = getThemeClickableTextAppearance(theme),
    onClick: (widget: View) -> Unit
): ClickableSpan =
    ClickableSpan(
        appearance = appearance,
        onClick = onClick
    )

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action and [appearance].
 */
public fun ClickableSpan(
    appearance: ClickableTextAppearance,
    onClick: (widget: View) -> Unit
): ClickableSpan =
    object : CustomizableClickableSpan(appearance) {
        override fun onClick(widget: View) =
            onClick(widget)
    }

private fun getThemeClickableTextAppearance(theme: Resources.Theme): ClickableTextAppearance =
    theme.getClickableTextAppearance()