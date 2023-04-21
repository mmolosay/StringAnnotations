package io.github.mmolosays.stringannotations.views.processor

import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import io.github.mmolosays.stringannotations.BaseClickableAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseColorAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseDecorationAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseSizeAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseStyleAnnotationProcessor
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.processor.AnnotationProcessor
import io.github.mmolosays.stringannotations.processor.confaltor.ValuesConfaltor
import io.github.mmolosays.stringannotations.processor.parser.DefaultValuesParser
import io.github.mmolosays.stringannotations.processor.parser.ValuesParser
import io.github.mmolosays.stringannotations.processor.token.Tokenizer
import io.github.mmolosays.stringannotations.views.ViewsAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsArguments
import io.github.mmolosays.stringannotations.views.ViewsSpan
import io.github.mmolosays.stringannotations.views.clickable.CustomizableClickableSpan

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
 * `AnnotationProcessor` builders.
 */

/**
 * Builder for [AnnotationProcessor] implementations for single annotation type for Android Views UI.
 * Employs [AbstractAnnotationProcessor] inside.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method.
 */
public fun <V> ViewsAnnotationProcessor(
    tokenizer: Tokenizer,
    conflator: ValuesConfaltor<V>,
    parser: ValuesParser = DefaultValuesParser,
    values: ViewsArguments.() -> QualifiedList<V>?,
    factory: (value: V) -> ViewsSpan?
): ViewsAnnotationProcessor =
    AnnotationProcessor(
        tokenizer = tokenizer,
        conflator = conflator,
        parser = parser,
        values = values,
        factory = factory
    )

/**
 * Implementation of [BaseColorAnnotationProcessor] for Android Views UI.
 */
internal fun BackgroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseColorAnnotationProcessor {
        BackgroundColorSpan(it)
    }

/**
 * Implementation of [BaseColorAnnotationProcessor] for Android Views UI.
 */
internal fun ForegroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseColorAnnotationProcessor {
        ForegroundColorSpan(it)
    }

/**
 * Implementation of [CustomizableClickableSpan] for Android Views UI.
 */
internal fun ClickableAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseClickableAnnotationProcessor {
        CustomizableClickableSpan(it)
    }

/**
 * Implementation of [BaseStyleAnnotationProcessor] for Android Views UI.
 */
internal fun StyleAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseStyleAnnotationProcessor {
        StyleSpan(it)
    }

/**
 * Implementation of [BaseDecorationAnnotationProcessor] for Android Views UI.
 */
internal fun DecorationAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseDecorationAnnotationProcessor {
        when (it) {
            TextDecoration.Underline -> UnderlineSpan()
            TextDecoration.Striketrhough -> StrikethroughSpan()
            else -> null as ViewsSpan? // fails type infering without explicit cast
        }
    }

/**
 * Implementation of [BaseSizeAnnotationProcessor] for Android Views UI.
 * Expects `TextSize.value` to be `pixels` units.
 */
internal fun SizeAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseSizeAnnotationProcessor {
        AbsoluteSizeSpan(it.value.toInt())
    }