package com.mmolosay.stringannotations.view.processor

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentsSet
import com.mmolosay.stringannotations.view.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.view.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.view.processor.parser.AsIsValueParser
import com.mmolosay.stringannotations.view.processor.parser.ValueParser
import com.mmolosay.stringannotations.view.processor.parser.arg.DefaultAnnotationArgumentParser
import com.mmolosay.stringannotations.view.processor.parser.arg.AnnotationArgumentParser
import com.mmolosay.stringannotations.view.processor.token.Token
import com.mmolosay.stringannotations.view.processor.token.Tokenizer

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
internal class StyleAnnotationProcessor : BaseArgsAnnotationProcessor<Token>() {

    override val tokenizer: Tokenizer = Tokenizer.Solid()
    override val valueParser: ValueParser<Token> = AsIsValueParser
    override val argParser: AnnotationArgumentParser = DefaultAnnotationArgumentParser
    override val conflator: ValuesConfaltor<Token> = StrategyConflator.Single()

    private val typefaceStyleAnnotationProcessor: TypefaceStyleAnnotationProcessor =
        TypefaceStyleAnnotationProcessor()

    override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        argumentsSet: ArgumentsSet?
    ): CharacterStyle? =
        super.parseAnnotation(context, annotation, argumentsSet)
            ?: typefaceStyleAnnotationProcessor.parseAnnotation(context, annotation, argumentsSet)

    override fun inferArguments(set: ArgumentsSet?): Arguments<Token>? =
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