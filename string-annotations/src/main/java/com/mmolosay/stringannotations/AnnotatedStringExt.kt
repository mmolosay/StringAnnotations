package com.mmolosay.stringannotations

import android.content.Context
import android.text.Spanned
import android.text.style.ClickableSpan
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.mmolosay.stringannotations.core.AnnotatedStrings

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
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * @throws IllegalStateException if library was not configured.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    clickables: List<ClickableSpan>,
    formatArgs: Array<out Any>,
    valueArgs: Array<out Any>
): Spanned =
    AnnotatedStrings.process(
        context = this,
        id = id,
        clickables = clickables,
        formatArgs = formatArgs,
        valueArgs = valueArgs
    )

/**
 * Simplified variant of [Context.getAnnotatedString] for cases, when there is no clickable spans.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    formatArgs: Array<out Any> = emptyArray(),
    valueArgs: Array<out Any> = emptyArray()
): Spanned =
    AnnotatedStrings.process(
        context = this,
        id = id,
        clickables = emptyList(),
        formatArgs = formatArgs,
        valueArgs = valueArgs
    )

/**
 * Simplified variant of [Context.getAnnotatedString] for cases, when there is
 * neither clickable spans nor value arguments.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    AnnotatedStrings.process(
        context = this,
        id = id,
        clickables = emptyList(),
        formatArgs = formatArgs,
        valueArgs = emptyArray()
    )

/**
 * Simplified variant of [Context.getAnnotatedString] for cases, when there is only one clickable span.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    clickable: ClickableSpan,
    vararg formatArgs: Any
): Spanned =
    AnnotatedStrings.process(this, id, listOf(clickable), *formatArgs)

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * Receiver [Fragment] must be attached to context, otherwise [IllegalStateException] will be thrown.
 *
 * @throws IllegalStateException if fragment was not attached to context.
 * @throws IllegalStateException if library was not configured.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    clickables: List<ClickableSpan>,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(id, clickables, *formatArgs)

/**
 * Simplified variant of [Fragment.getAnnotatedString] for cases, when there is no clickable spans.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(id, emptyList(), *formatArgs)

/**
 * Simplified variant of [Fragment.getAnnotatedString] for cases, when there is only one clickable span.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    clickable: ClickableSpan,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(id, listOf(clickable), *formatArgs)