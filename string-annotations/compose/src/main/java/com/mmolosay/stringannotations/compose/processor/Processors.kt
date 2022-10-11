@file:Suppress("FunctionName")

package com.mmolosay.stringannotations.compose.processor

import android.graphics.Typeface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.TextDecoration
import com.mmolosay.stringannotations.compose.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.ComposeArguments
import com.mmolosay.stringannotations.internal.processor.BaseClickableAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseColorAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseDecorationAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseSizeAnnotationProcessor
import com.mmolosay.stringannotations.internal.processor.BaseStyleAnnotationProcessor
import com.mmolosay.stringannotations.processor.AbstractAnnotationProcessor
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.parser.DefaultValuesParser
import com.mmolosay.stringannotations.processor.parser.ValuesParser
import com.mmolosay.stringannotations.processor.token.Tokenizer
import androidx.compose.ui.text.style.TextDecoration as ComposeTextDecoration

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
 * `AnnotationProcessor` builders.
 */

/**
 * Builder for [AnnotationProcessor] implementations for single annotation type for Compose UI.
 * Employs [AbstractAnnotationProcessor] inside.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method.
 */
public fun <V> ComposeAnnotationProcessor(
    tokenizer: Tokenizer,
    conflator: ValuesConfaltor<V>,
    parser: ValuesParser = DefaultValuesParser,
    values: ComposeArguments.() -> QualifiedList<V>?,
    factory: (value: V) -> ComposeSpan?
): ComposeAnnotationProcessor =
    AnnotationProcessor(
        tokenizer = tokenizer,
        conflator = conflator,
        parser = parser,
        values = values,
        factory = factory
    )

/**
 * Implementation of [BaseClickableAnnotationProcessor] for Compose UI.
 */
internal fun ClickableAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseClickableAnnotationProcessor {
        ComposeSpan.of(it)
    }

/**
 * Implementation of [BaseColorAnnotationProcessor] for Compose UI.
 */
internal fun BackgroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseColorAnnotationProcessor {
        ComposeSpan.of(SpanStyle(background = Color(it)))
    }

/**
 * Implementation of [BaseColorAnnotationProcessor] for Compose UI.
 */
internal fun ForegroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseColorAnnotationProcessor {
        ComposeSpan.of(SpanStyle(color = Color(it)))
    }

/**
 * Implementation of [BaseDecorationAnnotationProcessor] for Compose UI.
 */
internal fun DecorationAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseDecorationAnnotationProcessor {
        when (it) {
            TextDecoration.Underline -> SpanStyle(textDecoration = ComposeTextDecoration.Underline)
            TextDecoration.Striketrhough -> SpanStyle(textDecoration = ComposeTextDecoration.LineThrough)
            else -> null
        }?.let { style -> ComposeSpan.of(style) }
    }

/**
 * Implementation of [BaseSizeAnnotationProcessor] for Compose UI.
 * Expects `TextSize.value` to be `SP` units.
 */
@OptIn(ExperimentalUnitApi::class)
internal fun SizeAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseSizeAnnotationProcessor {
        ComposeSpan.of(SpanStyle(fontSize = TextUnit(it.value, TextUnitType.Sp)))
    }

/**
 * Implementation of [BaseStyleAnnotationProcessor] for Compose UI.
 */
internal fun StyleAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseStyleAnnotationProcessor {
        when (it) {
            Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
            Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
            Typeface.BOLD_ITALIC ->
                SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            else -> null
        }?.let { style -> ComposeSpan.of(style) }
    }