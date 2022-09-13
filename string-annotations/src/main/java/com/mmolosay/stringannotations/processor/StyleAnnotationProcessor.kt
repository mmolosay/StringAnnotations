package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.core.AnnotationTag
import com.mmolosay.stringannotations.core.BaseAnnotationProcessor
import com.mmolosay.stringannotations.core.Tokenizer
import com.mmolosay.stringannotations.parser.TokenParser
import com.mmolosay.stringannotations.values.DefaultValueArgParser
import com.mmolosay.stringannotations.values.Evaluator
import com.mmolosay.stringannotations.values.ValueArgParser

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

/**
 * `AnnotationProcessor` for style annotation type.
 */
internal class StyleAnnotationProcessor : BaseAnnotationProcessor<Nothing>() {

    override val tokenizer: Tokenizer = Tokenizer.Solid()
    override val tokenParser: TokenParser<Nothing>? = null
    override val valueArgParser: ValueArgParser = DefaultValueArgParser
    override val evaluator: Evaluator<Nothing> = Evaluator.Single()

    private val typefaceStyleAnnotationProcessor: TypefaceStyleAnnotationProcessor =
        TypefaceStyleAnnotationProcessor()

    override fun parseAnnotation(
        context: Context,
        tokens: Sequence<AnnotationTag.Token>,
        args: ValueArgs?
    ): CharacterStyle? =
        when {
            tokens.contains(tokenUnderline) -> UnderlineSpan()
            tokens.contains(tokenStrikethrough) -> StrikethroughSpan()
            else -> typefaceStyleAnnotationProcessor.parseAnnotation(context, tokens, args)
        }

    override fun inferArgs(args: ValueArgs?): List<Nothing>? =
        null

    override fun makeSpan(value: Nothing): CharacterStyle? =
        null

    private companion object {
        val tokenUnderline = AnnotationTag.Token("underline")
        val tokenStrikethrough = AnnotationTag.Token("strikethrough")
    }
}