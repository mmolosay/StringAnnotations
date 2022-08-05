package com.mmolosay.stringannotations.spans

import android.text.TextPaint
import android.text.style.ClickableSpan
import com.mmolosay.stringannotations.ClickableTextAppearance

/**
 * [ClickableSpan] with flexible customization.
 */
internal abstract class CustomizableClickableSpan(
    private val appearance: ClickableTextAppearance
) : ClickableSpan() {

    override fun updateDrawState(paint: TextPaint) {
        super.updateDrawState(paint)
        applyAppearance(paint)
    }

    private fun applyAppearance(paint: TextPaint) {
        paint.isUnderlineText = appearance.underlineText
        paint.color = appearance.textColor
    }
}
