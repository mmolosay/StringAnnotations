package io.github.mmolosays.stringannotations.views

import android.content.Context
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.github.mmolosays.stringannotations.processor.parser.CommonValueParser

/*
 * Copyright 2023 Mikhail Malasai
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
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * @param id resource id of annotated string.
 * @param arguments annotation arguments to be used instead of value placeholders
 * (like "`$arg$color$0`" for [CommonValueParser]).
 * @param processor an annotaion processor to be used.
 * @param formatArgs formatting arguments to be substituted instead of positional argument (like "`%1$s`").
 * See also: [Formatting strings](https://developer.android.com/guide/topics/resources/string-resource#formatting-strings).
 */
public fun Context.getAnnotatedString(
    @StringRes id: Int,
    arguments: ViewsArguments,
    processor: ViewsAnnotationProcessor,
    vararg formatArgs: Any,
): Spanned =
    AnnotatedStrings.process(
        string = getSpannedString(id),
        arguments = arguments,
        processor = processor,
        formatArgs = formatArgs,
    )

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * Receiver [Fragment] must be attached to context, otherwise [IllegalStateException] will be thrown.
 *
 * @param id resource id of annotated string.
 * @param arguments annotation arguments to be used instead of value placeholders
 * (like "`$arg$color$0`" for [CommonValueParser]).
 * @param processor an annotaion processor to be used.
 * @param formatArgs formatting arguments to be substituted instead of positional argument (like "`%1$s`").
 * See also: [Formatting strings](https://developer.android.com/guide/topics/resources/string-resource#formatting-strings).
 *
 * @throws IllegalStateException if fragment is not attached to a context.
 */
public fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    arguments: ViewsArguments,
    processor: ViewsAnnotationProcessor,
    vararg formatArgs: Any,
): Spanned =
    requireContext().getAnnotatedString(
        id = id,
        arguments = arguments,
        processor = processor,
        formatArgs = formatArgs,
    )

private fun Context.getSpannedString(@StringRes id: Int): SpannedString =
    this.resources.getText(id) as SpannedString