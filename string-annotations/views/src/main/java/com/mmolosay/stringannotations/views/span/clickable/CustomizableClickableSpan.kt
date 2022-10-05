package com.mmolosay.stringannotations.views.span.clickable

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.mmolosay.stringannotations.views.args.Clickable

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
 * [Clickable] with customizable appearance.
 */
internal class CustomizableClickableSpan(
    private val clickable: Clickable
) : ClickableSpan() {

    override fun onClick(p0: View) {
        clickable.action.click()
    }

    override fun updateDrawState(paint: TextPaint) {
        super.updateDrawState(paint)
        applyAppearance(paint)
    }

    private fun applyAppearance(paint: TextPaint) {
        paint.isUnderlineText = clickable.appearance.underlineText
        paint.color = clickable.appearance.textColor
    }
}
