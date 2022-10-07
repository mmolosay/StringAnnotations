package com.mmolosay.stringannotations.views.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentsBuilder
import com.mmolosay.stringannotations.internal.args.ArgumentsBuilderImpl
import com.mmolosay.stringannotations.views.ViewsArguments

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
 * Utilities and extensions for `Arguments`.
 */

/**
 * Assembles [ViewsArguments] in declarative style.
 */
public fun Arguments(
    scope: ArgumentsBuilder<Clickable>.() -> Unit
): ViewsArguments =
    builder().apply(scope).build()

/**
 * Returns instance of empty [ViewsArguments].
 */
public fun emptyArguments(): ViewsArguments =
    object : ViewsArguments {
        override val colors = Arguments.Colors(emptyList())
        override val clickables = Arguments.Clickables<Clickable>(emptyList())
        override val styles = Arguments.Styles(emptyList())
        override val sizes = Arguments.Sizes(emptyList())
    }

private fun builder(): ArgumentsBuilder<Clickable> =
    ArgumentsBuilderImpl()