package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextDecoration
import com.mmolosay.stringannotations.args.types.TextSize

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
 * Builder for [Arguments].
 */
internal fun <C : ClickOwner> Arguments(
    colors: List<Int>,
    clickables: List<C>,
    styles: List<Int>,
    decorations: List<TextDecoration>,
    sizes: List<TextSize>,
): Arguments<C> =
    object : Arguments<C> {
        override val colors = Arguments.Colors(colors)
        override val clickables = Arguments.Clickables(clickables)
        override val styles = Arguments.Styles(styles)
        override val decorations = Arguments.Decorations(decorations)
        override val sizes = Arguments.Sizes(sizes)
    }