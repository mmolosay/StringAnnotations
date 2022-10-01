package com.mmolosay.stringannotations.view.span.clickable

import android.content.Context
import android.content.res.Resources
import android.text.style.ClickableSpan
import android.view.View

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

/*
 * ClickableSpan builder functions.
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
    val themeAppearance = ClickableTextAppearance.from(theme)
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
    val themeAppearance = ClickableTextAppearance.from(context.theme)
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
    appearance: ClickableTextAppearance = ClickableTextAppearance.from(theme),
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
