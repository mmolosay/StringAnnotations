package com.mmolosay.stringannotations.spans

import android.text.style.ClickableSpan
import android.view.View
import com.mmolosay.stringannotations.lib.ClickableTextAppearance
import com.mmolosay.stringannotations.lib.StringAnnotations

/*
 * Spans builder functions.
 */

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action and appearance.
 *
 * Builder [appearanceBuilder] will be scoped to [ClickableTextAppearance],
 * set in [StringAnnotations.Builder.clickableTextAppearance].
 */
public fun ClickableSpan(
    appearanceBuilder: ClickableTextAppearance.() -> ClickableTextAppearance,
    onClick: (widget: View) -> Unit
): ClickableSpan =
    ClickableSpan(
        appearance = appearanceBuilder(getDefaultClickableTextAppearance()),
        onClick = onClick
    )

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action and appearance.
 */
public fun ClickableSpan(
    appearance: ClickableTextAppearance = getDefaultClickableTextAppearance(),
    onClick: (widget: View) -> Unit
): ClickableSpan =
    object : CustomizableClickableSpan(appearance) {
        override fun onClick(widget: View) =
            onClick(widget)
    }

private fun getDefaultClickableTextAppearance(): ClickableTextAppearance =
    StringAnnotations.requireClickableTextAppearance()