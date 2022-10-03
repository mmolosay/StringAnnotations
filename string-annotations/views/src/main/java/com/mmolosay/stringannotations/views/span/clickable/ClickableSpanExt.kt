package com.mmolosay.stringannotations.views.span.clickable

import android.content.res.Resources
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan
import com.mmolosay.stringannotations.views.utils.ThemeUtils
import com.mmolosay.stringannotations.views.utils.ThemeUtils.getClickableSpanAppearance

/**
 * Retrieves [ClickableSpan.Appearance] from specified [theme].
 *
 * @see ThemeUtils.getClickableSpanAppearance
 */
public fun ClickableSpan.Appearance.Companion.from(theme: Resources.Theme): ClickableSpan.Appearance =
    theme.getClickableSpanAppearance()