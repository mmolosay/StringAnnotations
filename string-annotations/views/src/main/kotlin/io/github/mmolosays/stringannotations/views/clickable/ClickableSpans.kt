package io.github.mmolosays.stringannotations.views.clickable

import android.content.Context
import android.content.res.Resources
import io.github.mmolosays.stringannotations.args.types.ClickOwner
import io.github.mmolosays.stringannotations.views.args.Clickable

/*
 * Copyright 2023 Mikhail Malasai
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
 * `Clickable` builder functions.
 */

/**
 * Creates new instance of [Clickable] with specified click [action] and appearance.
 * Appearance [builder] will be scoped to [Clickable.Appearance], obtained from [theme].
 */
public fun Clickable(
    theme: Resources.Theme,
    builder: Clickable.Appearance.() -> Clickable.Appearance,
    action: ClickOwner.ClickAction,
): Clickable {
    val themeAppearance = Clickable.Appearance.from(theme)
    val appearance = builder(themeAppearance)
    return Clickable(
        appearance = appearance,
        action = action,
    )
}

/**
 * More sophisticated version of builder, which pulls theme out of [context].
 */
public fun Clickable(
    context: Context,
    builder: Clickable.Appearance.() -> Clickable.Appearance,
    action: ClickOwner.ClickAction,
): Clickable {
    val themeAppearance = Clickable.Appearance.from(context.theme)
    val appearance = builder(themeAppearance)
    return Clickable(
        appearance = appearance,
        action = action,
    )
}

/**
 * Creates new instance of [Clickable] with specified click [action].
 * The [appearance] will be obrained from [theme].
 */
public fun Clickable(
    theme: Resources.Theme,
    appearance: Clickable.Appearance = Clickable.Appearance.from(theme),
    action: ClickOwner.ClickAction,
): Clickable =
    Clickable(
        appearance = appearance,
        action = action,
    )
