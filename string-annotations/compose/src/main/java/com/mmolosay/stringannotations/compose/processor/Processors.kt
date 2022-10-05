@file:Suppress("FunctionName")

package com.mmolosay.stringannotations.compose.processor

import android.graphics.Typeface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.QualifiedList
import com.mmolosay.stringannotations.compose.internal.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.internal.ComposeSpan
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
 * Builder for [AnnotationProcessor] implementations for single annotation type for Compose UI.
 * Employs [AbstractAnnotationProcessor] inside.
 *
 * One should use it, if they won't override [AnnotationProcessor.parseAnnotation] method,
 * implemented in [AbstractAnnotationProcessor].
 */
public fun <V> ComposeAnnotationProcessor(
    tokenizer: Tokenizer,
    conflator: ValuesConfaltor<V>,
    parser: ValuesParser = DefaultValuesParser,
    values: Arguments.() -> QualifiedList<V>?,
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
 * Implementation of [BaseColorAnnotationProcessor] for Compose UI.
 */
internal fun BackgroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseColorAnnotationProcessor {
        SpanStyle(background = Color(it))
    }

/**
 * Implementation of [BaseColorAnnotationProcessor] for Compose UI.
 */
internal fun ForegroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseColorAnnotationProcessor {
        SpanStyle(color = Color(it))
    }

/**
 * Implementation of [BaseClickableAnnotationProcessor] for Compose UI.
 */
internal fun ClickableAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseClickableAnnotationProcessor {
        SpanStyle() // TODO: https://developer.android.com/jetpack/compose/text#click-with-annotation
    }

/**
 * Implementation of [BaseTypefaceStyleAnnotationProcessor] for Compose UI.
 */
internal fun TypefaceStyleAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseTypefaceStyleAnnotationProcessor {
        when (it) {
            Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
            Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
            Typeface.BOLD_ITALIC ->
                SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            else -> null
        }
    }

/**
 * Implementation of [BaseDecorationAnnotationProcessor] for Compose UI.
 */
internal fun DecorationAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseDecorationAnnotationProcessor(
        underlineSpansFactory = { SpanStyle(textDecoration = TextDecoration.Underline) },
        strikethroughSpansFactory = { SpanStyle(textDecoration = TextDecoration.LineThrough) },
    )

/**
 * Implementation of [BaseAbsoluteSizeAnnotationProcessor] for Compose UI.
 */
@OptIn(ExperimentalUnitApi::class)
internal fun AbsoluteSizeAnnotationProcessor(): ComposeAnnotationProcessor =
    BaseAbsoluteSizeAnnotationProcessor {
        SpanStyle(
            fontSize = TextUnit(it.toFloat(), TextUnitType.Sp)
        )
    }