package com.mmolosay.stringannotations.view.utils

import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.util.TypedValue
import com.mmolosay.stringannotations.view.R
import com.mmolosay.stringannotations.view.span.clickable.ClickableTextAppearance

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