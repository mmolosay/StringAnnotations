package com.example.stringannotations.spans

import android.text.style.ClickableSpan
import android.view.View
import com.example.stringannotations.lib.ClickableTextAppearance
import com.example.stringannotations.lib.StringAnnotations

/*
 * Spans builder functions.
 */

/**
 * Creates new instance of [ClickableSpan] with specified [onClick] action and appearance.
 *
 * Builder [appearanceBuilder] will be scoped to [ClickableTextAppearance],
 * set in [StringAnnotations.Builder.clickableTextAppearance].
 */
fun ClickableSpan(
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
fun ClickableSpan(
    appearance: ClickableTextAppearance = getDefaultClickableTextAppearance(),
    onClick: (widget: View) -> Unit
): ClickableSpan =
    object : CustomizableClickableSpan(appearance) {
        override fun onClick(widget: View) =
            onClick(widget)
    }

private fun getDefaultClickableTextAppearance(): ClickableTextAppearance =
    StringAnnotations.requireClickableTextAppearance()