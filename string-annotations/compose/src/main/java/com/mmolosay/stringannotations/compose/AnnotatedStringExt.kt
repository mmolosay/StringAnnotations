package com.mmolosay.stringannotations.compose

import android.content.res.Resources
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import com.mmolosay.stringannotations.compose.internal.ComposeArguments

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
 * Extensions for convenience of use.
 */

/**
 * Returns [AnnotatedString], associated with a specified string resource [id] with `<annotation>`s.
 *
 * @param id resource id of annotated string.
 * @param arguments annotation arguments to be substituted instead of placeholders.
 * @param formatArgs formatting arguments to be substituted.
 */
@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    arguments: ComposeArguments,
    vararg formatArgs: Any
): AnnotatedString =
    AnnotatedStrings.process(
        string = spannedStringResource(id),
        arguments = arguments,
        formatArgs = formatArgs
    )

/**
 * Simplified variant of [annotatedStringResource] for cases, when there is no annotation arguments.
 */
@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    vararg formatArgs: Any
): AnnotatedString =
    AnnotatedStrings.process(
        string = spannedStringResource(id),
        arguments = null,
        formatArgs = formatArgs
    )

/**
 * Get string annotation of clickable type of `this` [AnnotatedString], that contains
 * specified [offset] in its placement range.
 *
 * @return first annotation of `null`, if there's no such.
 */
public fun AnnotatedString.getClickableAnnotationAt(offset: Int): AnnotatedString.Range<String>? =
    this.getStringAnnotations(
        tag = "clickable",
        start = offset,
        end = offset
    ).firstOrNull()

/**
 * Returns [SpannedString], associated with a specified string resource [id] with `<annotation>`s.
 */
@Composable
@ReadOnlyComposable
private fun spannedStringResource(@StringRes id: Int): SpannedString {
    val resources = resources()
    return resources.getText(id) as SpannedString
}

/**
 * Copy of [androidx.compose.ui.res.resources] function, since it declared as `internal`.
 */
// TODO: replace with original function, if it becomes public
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}