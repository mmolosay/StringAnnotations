package com.mmolosay.stringannotations.view.span.clickable

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan as ClickableSpanData

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * [ClickableSpan] with flexible customization.
 */
internal class CustomizableClickableSpan(
    private val span: ClickableSpanData
) : ClickableSpan() {

    override fun onClick(p0: View) {
        span.action.click()
    }

    override fun updateDrawState(paint: TextPaint) {
        super.updateDrawState(paint)
        applyAppearance(paint)
    }

    private fun applyAppearance(paint: TextPaint) {
        paint.isUnderlineText = span.appearance.underlineText
        paint.color = span.appearance.textColor
    }
}
