@file:Suppress("FunctionName")

package com.mmolosay.stringannotations.internal.processor

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextSize
import com.mmolosay.stringannotations.processor.AbstractAnnotationProcessor
import com.mmolosay.stringannotations.processor.AbstractMasterAnnotationProcessor.AnnotationTypes
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.token.Token
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
 * `AnnotationProcessor` builders.
 */

/**
 * `AnnotationProcessor` for any color annotation type.
 */
public fun <S> BaseColorAnnotationProcessor(
    factory: (value: Int) -> S?
): AnnotationProcessor<S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { values.colors },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.clickable] annotation type.
 */
@Suppress("UNCHECKED_CAST") // price for not including actual AnnotationValues type in generic
public fun <C : ClickOwner, S> BaseClickableAnnotationProcessor(
    factory: (value: C) -> S?
): AnnotationProcessor<S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Solid(),
        conflator = StrategyConflator.First(),
        values = { values.clickables as? QualifiedList<C> },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.style] annotation type.
 */
public fun <S> BaseStyleAnnotationProcessor(
    factory: (value: Int) -> S?
): AnnotationProcessor<S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { values.styles },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.decoration] annotation type.
 */
public fun <S> BaseDecorationAnnotationProcessor(
    underlineSpansFactory: () -> S?,
    strikethroughSpansFactory: () -> S?,
): AnnotationProcessor<S> =
    object : AbstractAnnotationProcessor<Token, S>() {

        override val tokenizer: Tokenizer = Tokenizer.Solid()
        override val conflator: ValuesConfaltor<Token> = StrategyConflator.First()

        override fun parseValue(token: Token, arguments: Arguments?): Token =
            token // since requires value of type Token, we just pass it

        override fun Arguments.getValues(): QualifiedList<Token>? =
            null // TODO: update once added to Arguments

        override fun makeSpan(value: Token): S? =
            when (value) {
                Tokens.underline -> underlineSpansFactory()
                Tokens.strikethrough -> strikethroughSpansFactory()
                else -> null
            }
    }

/**
 * `AnnotationProcessor` for [AnnotationTypes.size] annotation type.
 * [TextSize.value] units (pixels, SPs, etc.) are expected to be defined in concrete
 * implementation.
 */
public fun <S> BaseSizeAnnotationProcessor(
    factory: (value: TextSize) -> S?
): AnnotationProcessor<S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { values.sizes },
        factory = factory
    )

private object Tokens {
    val underline = Token("underline")
    val strikethrough = Token("strikethrough")
}