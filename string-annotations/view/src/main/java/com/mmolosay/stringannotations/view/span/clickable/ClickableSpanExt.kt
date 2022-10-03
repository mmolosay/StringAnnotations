package com.mmolosay.stringannotations.view.span.clickable

import android.content.res.Resources
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan
import com.mmolosay.stringannotations.view.utils.ThemeUtils
import com.mmolosay.stringannotations.view.utils.ThemeUtils.getClickableSpanAppearance

/**
 * Retrieves [ClickableSpan.Appearance] from specified [theme].
 *
 * @see ThemeUtils.getClickableSpanAppearance
 */
public fun ClickableSpan.Appearance.Companion.from(theme: Resources.Theme): ClickableSpan.Appearance =
    theme.getClickableSpanAppearance()