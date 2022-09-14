package com.mmolosay.stringannotations

import android.content.Context
import android.text.Spanned
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.mmolosay.stringannotations.args.ValueArgs

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
 * @param id resource id of annotated string.
 * @param valueArgs value placeholder arguments.
 * @param formatArgs formatting arguments to be substituted.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    valueArgs: ValueArgs,
    vararg formatArgs: Any,
): Spanned =
    AnnotatedStrings.process(
        context = this,
        id = id,
        formatArgs = formatArgs,
        valueArgs = valueArgs
    )

/**
 * Simplified variant of [Context.getAnnotatedString] for cases, when there is no value arguments.
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    AnnotatedStrings.process<ValueArgs>(
        context = this,
        id = id,
        formatArgs = formatArgs
    )

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * Receiver [Fragment] must be attached to context, otherwise [IllegalStateException] will be thrown.
 *
 * @param id resource id of annotated string.
 * @param valueArgs value placeholder arguments.
 * @param formatArgs formatting arguments to be substituted.
 *
 * @throws IllegalStateException if fragment was not attached to context.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    valueArgs: ValueArgs,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(
        id = id,
        formatArgs = formatArgs,
        valueArgs = valueArgs
    )

/**
 * Simplified variant of [Fragment.getAnnotatedString] for cases, when there is no value arguments.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(
        id = id,
        formatArgs = formatArgs
    )