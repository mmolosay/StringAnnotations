package io.github.mmolosays.stringannotations.compose.processor

import android.graphics.Typeface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import io.github.mmolosays.stringannotations.BaseClickableAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseColorAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseDecorationAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseSizeAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseStyleAnnotationProcessor
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize.SizeUnit
import io.github.mmolosays.stringannotations.compose.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.ComposeArguments
import io.github.mmolosays.stringannotations.processor.AbstractMasterAnnotationProcessor
import io.github.mmolosays.stringannotations.processor.parser.CommonValueParser
import io.github.mmolosays.stringannotations.processor.parser.ValueParser
import androidx.compose.ui.text.style.TextDecoration as ComposeTextDecoration

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

/**
 * Implementation of [AbstractMasterAnnotationProcessor] for Compose UI.
 *
 * One should inherit this class in order to extend out-of-the-box annotaiton types with
 * custom ones.
 */
public open class MasterAnnotationProcessor(
    override val defaultValueParser: ValueParser = CommonValueParser,
) :
    AbstractMasterAnnotationProcessor<ComposeArguments, ComposeSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseColorAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            ComposeSpan.of(SpanStyle(background = Color(it)))
        }

    override fun createClickableAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseClickableAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            ComposeSpan.of(it)
        }

    override fun createForegroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseColorAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            ComposeSpan.of(SpanStyle(color = Color(it)))
        }

    override fun createDecorationAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseDecorationAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            when (it) {
                TextDecoration.Underline -> SpanStyle(
                    textDecoration = ComposeTextDecoration.Underline,
                )

                TextDecoration.Striketrhough -> SpanStyle(
                    textDecoration = ComposeTextDecoration.LineThrough,
                )

                else -> null
            }
                ?.let { style -> ComposeSpan.of(style) }
        }

    override fun createSizeAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseSizeAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            when (it.unit) {
                SizeUnit.Sp -> TextUnit(it.value, TextUnitType.Sp)
                SizeUnit.Px -> throw IllegalArgumentException("Compose TextUnit does not support pixel sizes")
                else -> null
            }
                ?.let { size -> ComposeSpan.of(SpanStyle(fontSize = size)) }
        }

    override fun createStyleAnnotationProcessor(): ComposeAnnotationProcessor =
        BaseStyleAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            when (it) {
                Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
                Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
                Typeface.BOLD_ITALIC ->
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                    )

                else -> null
            }
                ?.let { style -> ComposeSpan.of(style) }
        }
}