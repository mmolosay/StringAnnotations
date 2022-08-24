package com.mmolosay.stringannotations

import android.content.res.Resources
import com.mmolosay.stringannotations.utils.ThemeUtils.getClickableTextAppearance

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
 * Extensions for convenience use.
 */

/**
 * Retrieves [ClickableTextAppearance] from specified [theme].
 *
 * Firstly it will try to resolve style, set as attribute [R.attr.clickableTextAppearance] value
 * in the specified [theme].
 * If it is `null`, then will use default [R.style.Base_ClickableTextAppearance] style.
 */
public fun ClickableTextAppearance.Companion.from(theme: Resources.Theme): ClickableTextAppearance =
    theme.getClickableTextAppearance()