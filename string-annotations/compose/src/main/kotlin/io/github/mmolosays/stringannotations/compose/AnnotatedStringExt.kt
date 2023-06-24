package io.github.mmolosays.stringannotations.compose

import android.content.res.Resources
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import io.github.mmolosays.stringannotations.compose.args.Clickable
import io.github.mmolosays.stringannotations.compose.internal.SpanProcessor
import io.github.mmolosays.stringannotations.compose.processor.MasterAnnotationProcessor
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
 * Default [ComposeAnnotationProcessor] to be used.
 *
 * As for any other [ProvidableCompositionLocal], if you want to provide your own implementation of
 * annotation processor, you should do it in the root of the application's UI.
 */
public val LocalStringAnnotationProcessor: ProvidableCompositionLocal<ComposeAnnotationProcessor> =
    staticCompositionLocalOf {
        MasterAnnotationProcessor(
            defaultValueParser = CommonValueParser,
        )
    }

/**
 * Returns [AnnotatedString], associated with a specified string resource [id] with `<annotation>`s.
 *
 * @param id resource id of annotated string.
 * @param arguments annotation arguments to be used instead of value placeholders
 * (like "`$arg$color$0`" for [CommonValueParser]).
 * @param processor an annotaion processor to be used.
 * Use the other variant of this function, if you want to use default annotation processor.
 * @param formatArgs formatting arguments to be substituted instead of positional argument (like "`%1$s`").
 * See also: [Formatting strings](https://developer.android.com/guide/topics/resources/string-resource#formatting-strings).
 */
@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    arguments: ComposeArguments,
    processor: ComposeAnnotationProcessor,
    vararg formatArgs: Any,
): AnnotatedString =
    AnnotatedStrings.process(
        string = spannedStringResource(id),
        arguments = arguments,
        processor = processor,
        formatArgs = formatArgs,
    )

/**
 * Variant of [annotatedStringResource] for cases, when you want to use an instance
 * of an annotation processor set in [LocalStringAnnotationProcessor].
 */
@Composable
@ReadOnlyComposable
public fun annotatedStringResource(
    @StringRes id: Int,
    arguments: ComposeArguments,
    vararg formatArgs: Any,
): AnnotatedString =
    AnnotatedStrings.process(
        string = spannedStringResource(id),
        arguments = arguments,
        processor = LocalStringAnnotationProcessor.current,
        formatArgs = formatArgs,
    )

/**
 * Invoke click action of [Clickable] from [clickables] list,
 * that corresponds to [getClickableAnnotationAt] specified [offset].
 */
public fun AnnotatedString.onClick(
    offset: Int,
    clickables: List<Clickable>,
) {
    this.getClickableAnnotationAt(offset)?.let { annotation ->
        clickables
            .find { annotation.item == it.annotation }
            ?.run { onClick() }
    }
}

/**
 * Get string annotation of clickable type of `this` [AnnotatedString], that contains
 * specified [offset] in its placement range.
 *
 * @return first annotation of `null`, if there's no such.
 *
 * @see [AnnotatedString.getStringAnnotations]
 */
public fun AnnotatedString.getClickableAnnotationAt(offset: Int): AnnotatedString.Range<String>? =
    this.getStringAnnotations(
        tag = SpanProcessor.ClickableTag,
        start = offset,
        end = offset,
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