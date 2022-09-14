package com.mmolosay.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.processor.parser.arg.DefaultValueArgParser
import com.mmolosay.stringannotations.processor.token.Token
import com.mmolosay.stringannotations.processor.token.Tokenizer
import com.mmolosay.stringannotations.processor.parser.arg.ValueArgParser
import com.mmolosay.stringannotations.processor.parser.AsIsTokenParser
import com.mmolosay.stringannotations.processor.parser.TokenParser
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor

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
 * `AnnotationProcessor` for "style" annotation type.
 */
internal class StyleAnnotationProcessor : BaseAnnotationProcessor<Token>() {

    override val tokenizer: Tokenizer = Tokenizer.Solid()
    override val tokenParser: TokenParser<Token> = AsIsTokenParser
    override val valueArgParser: ValueArgParser = DefaultValueArgParser
    override val conflator: ValuesConfaltor<Token> = StrategyConflator.Single()

    private val typefaceStyleAnnotationProcessor: TypefaceStyleAnnotationProcessor =
        TypefaceStyleAnnotationProcessor()

    override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        args: ValueArgs?
    ): CharacterStyle? =
        super.parseAnnotation(context, annotation, args)
            ?: typefaceStyleAnnotationProcessor.parseAnnotation(context, annotation, args)

    override fun inferArgs(args: ValueArgs?): List<Nothing>? =
        null

    override fun makeSpan(value: Token): CharacterStyle? =
        when (value) {
            tokenUnderline -> UnderlineSpan()
            tokenStrikethrough -> StrikethroughSpan()
            else -> null
        }

    private companion object {
        val tokenUnderline = Token("underline")
        val tokenStrikethrough = Token("strikethrough")
    }
}