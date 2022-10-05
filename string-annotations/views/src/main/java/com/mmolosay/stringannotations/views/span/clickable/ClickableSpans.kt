package com.mmolosay.stringannotations.views.span.clickable

import android.content.Context
import android.content.res.Resources
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

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
 * Creates new instance of [ClickableSpan] with specified click [action] and appearance.
 *
 * Appearance [builder] will be scoped to [ClickableSpan.Appearance], set in your [theme].
 */
public fun ClickableSpan(
    theme: Resources.Theme,
    builder: ClickableSpan.Appearance.() -> ClickableSpan.Appearance,
    action: ClickableSpan.ClickAction
): ClickableSpan {
    val themeAppearance = ClickableSpan.Appearance.from(theme)
    val appearance = builder(themeAppearance)
    return ClickableSpan(
        appearance = appearance,
        action = action
    )
}

/**
 * More sophisticated version, which pulls theme out of [context].
 *
 * @see [com.mmolosay.stringannotations.view.span.clickable.ClickableSpan]
 */
public fun ClickableSpan(
    context: Context,
    builder: ClickableSpan.Appearance.() -> ClickableSpan.Appearance,
    action: ClickableSpan.ClickAction
): ClickableSpan {
    val themeAppearance = ClickableSpan.Appearance.from(context.theme)
    val appearance = builder(themeAppearance)
    return ClickableSpan(
        appearance = appearance,
        action = action
    )
}

/**
 * Creates new instance of [ClickableSpan] with specified click [action].
 * The [appearance]'s default value will be retrieved from [theme].
 */
public fun ClickableSpan(
    theme: Resources.Theme,
    appearance: ClickableSpan.Appearance = ClickableSpan.Appearance.from(theme),
    action: ClickableSpan.ClickAction
): ClickableSpan =
    ClickableSpan(
        appearance = appearance,
        action = action
    )
