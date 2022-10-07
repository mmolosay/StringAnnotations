package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.internal.args.AbstractArgumentsBuilder

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

internal class ComposeArgumentsBuilder : AbstractArgumentsBuilder<Clickable>() {

    override fun build(): ComposeArguments =
        object : ComposeArguments {
            override val colors = Arguments.Colors(this@ComposeArgumentsBuilder.colors)
            override val clickables = Arguments.Clickables(this@ComposeArgumentsBuilder.clickables)
            override val styles = Arguments.Styles(this@ComposeArgumentsBuilder.styles)
            override val sizes = Arguments.Sizes(this@ComposeArgumentsBuilder.sizes)
        }
}