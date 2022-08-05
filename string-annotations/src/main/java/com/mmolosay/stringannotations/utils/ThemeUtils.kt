package com.mmolosay.stringannotations.utils

import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.util.TypedValue
import com.mmolosay.stringannotations.R
import com.mmolosay.stringannotations.ClickableTextAppearance

internal object ThemeUtils {

    /**
     * Retrieves [ClickableTextAppearance] from receiver [Resources.Theme].
     *
     * Firstly it will try to resolve style, set as attribute [R.attr.clickableTextAppearance] value
     * in the theme.
     * If it is `null`, then will use default [R.style.Base_ClickableTextAppearance] style.
     */
    fun Resources.Theme.getClickableTextAppearance(): ClickableTextAppearance {
        val value = TypedValue()
        val resolved = this.resolveAttribute(R.attr.clickableTextAppearance, value, true)
        val styleRes = if (resolved) value.data else R.style.Base_ClickableTextAppearance
        val typed = this.obtainStyledAttributes(styleRes, R.styleable.ClickableTextAppearance)
        return makeClickableTextAppearance(typed)
    }

    private fun makeClickableTextAppearance(typed: TypedArray) =
        ClickableTextAppearance(
            underlineText = typed.getBoolean(
                R.styleable.ClickableTextAppearance_underlineText,
                false
            ),
            textColor = typed.getColor(
                R.styleable.ClickableTextAppearance_android_textColor,
                Color.BLUE
            )
        )
}