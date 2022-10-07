package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.values.AnnotationValuesBuilder
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues
import com.mmolosay.stringannotations.internal.args.AnnotationValuesBuilderImpl

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
 * Extension for Arguments and stuff for convenience of use.
 */

/**
 * Assembles [Arguments] for Compose UI in declarative style.
 */
public fun Arguments(
    scope: AnnotationValuesBuilder<Clickable>.() -> Unit
): ComposeArguments =
    ComposeArguments(AnnotationValues(scope))

/**
 * Assembles [ComposeAnnotationValues] in a declarative style.
 */
public fun AnnotationValues(
    scope: AnnotationValuesBuilder<Clickable>.() -> Unit
): ComposeAnnotationValues =
    builder().apply(scope).build()

/**
 * Instantiates empty [ComposeAnnotationValues].
 */
public fun EmptyAnnotationValues(): ComposeAnnotationValues =
    builder().build()

private fun builder(): AnnotationValuesBuilderImpl<Clickable> =
    AnnotationValuesBuilderImpl()