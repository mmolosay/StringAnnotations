package com.mmolosay.stringannotations.views.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentsBuilder
import com.mmolosay.stringannotations.internal.args.ArgumentsBuilderImpl

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
 * Assembles [Arguments] for Android Views UI in declarative style.
 */
public fun Arguments(
    scope: ArgumentsBuilder<Clickable>.() -> Unit
): Arguments =
    builder().apply(scope).build()

private fun builder(): ArgumentsBuilderImpl<Clickable> =
    ArgumentsBuilderImpl()