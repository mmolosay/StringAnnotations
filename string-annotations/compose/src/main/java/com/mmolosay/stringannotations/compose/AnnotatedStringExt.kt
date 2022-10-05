package com.mmolosay.stringannotations.compose

import android.content.res.Resources
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import com.mmolosay.stringannotations.args.Arguments

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

// TODO: doc
@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    arguments: Arguments,
    vararg formatArgs: Any
): AnnotatedString {
    val resources = resources()
    val spanned = resources.getText(id) as SpannedString
    return AnnotatedStrings.process(
        string = spanned,
        arguments = arguments,
        formatArgs = formatArgs
    )
}

@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    vararg formatArgs: Any
): AnnotatedString {
    val resources = resources()
    val spanned = resources.getText(id) as SpannedString
    return AnnotatedStrings.process(
        string = spanned,
        arguments = null,
        formatArgs = formatArgs
    )
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