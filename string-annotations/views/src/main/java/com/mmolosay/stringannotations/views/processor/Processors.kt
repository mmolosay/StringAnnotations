@file:Suppress("FunctionName")

package com.mmolosay.stringannotations.views.processor

import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.QualifiedList
import com.mmolosay.stringannotations.internal.processor.AnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseAbsoluteSizeAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseClickableAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseColorAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseDecorationAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseTypefaceStyleAnnotationProcessor
import com.mmolosay.stringannotations.processor.AbstractAnnotationProcessor
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.parser.DefaultValuesParser
import com.mmolosay.stringannotations.processor.parser.ValuesParser
import com.mmolosay.stringannotations.processor.token.Tokenizer
import com.mmolosay.stringannotations.views.internal.ViewsAnnotationProcessor
import com.mmolosay.stringannotations.views.internal.ViewsSpan
import com.mmolosay.stringannotations.views.span.clickable.CustomizableClickableSpan

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
 * AnnotationProcessor builders.
 */

/**
 * Builder for [AnnotationProcessor] implementations for single annotation type for Android Views
 * system.
 * Employs [AbstractAnnotationProcessor] inside.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method,
 * implemented in [AbstractAnnotationProcessor].
 */
public fun <V> ViewsAnnotationProcessor(
    tokenizer: Tokenizer,
    conflator: ValuesConfaltor<V>,
    parser: ValuesParser = DefaultValuesParser,
    values: Arguments.() -> QualifiedList<V>?,
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
 * Implementation of [BaseColorAnnotationProcessor] for Android Views system.
 */
internal fun BackgroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseColorAnnotationProcessor {
        BackgroundColorSpan(it)
    }

/**
 * Implementation of [BaseColorAnnotationProcessor] for Android Views system.
 */
internal fun ForegroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseColorAnnotationProcessor {
        ForegroundColorSpan(it)
    }

/**
 * Implementation of [CustomizableClickableSpan] for Android Views system.
 */
internal fun ClickableAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseClickableAnnotationProcessor {
        CustomizableClickableSpan(it)
    }

/**
 * Implementation of [BaseTypefaceStyleAnnotationProcessor] for Android Views system.
 */
internal fun TypefaceStyleAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseTypefaceStyleAnnotationProcessor {
        StyleSpan(it)
    }

/**
 * Implementation of [BaseDecorationAnnotationProcessor] for Android Views system.
 */
internal fun DecorationAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseDecorationAnnotationProcessor(
        underlineSpansFactory = { UnderlineSpan() },
        strikethroughSpansFactory = { StrikethroughSpan() },
    )

/**
 * Implementation of [BaseAbsoluteSizeAnnotationProcessor] for Android Views system.
 * Expects `TextSize.value` to be `pixels` units.
 */
internal fun AbsoluteSizeAnnotationProcessor(): ViewsAnnotationProcessor =
    BaseAbsoluteSizeAnnotationProcessor {
        AbsoluteSizeSpan(it.value.toInt())
    }