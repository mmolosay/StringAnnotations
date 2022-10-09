@file:Suppress("FunctionName")

package com.mmolosay.stringannotations.internal.processor

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextDecoration
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

private typealias AnyArguments = Arguments<*>

/**
 * `AnnotationProcessor` for any color annotation type.
 */
public fun <A : AnyArguments, S> BaseColorAnnotationProcessor(
    factory: (value: Int) -> S?
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { colors },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.clickable] annotation type.
 */
@Suppress("UNCHECKED_CAST") // price for not including actual AnnotationValues type in generic
public fun <C : ClickOwner, A : Arguments<C>, S> BaseClickableAnnotationProcessor(
    factory: (value: C) -> S?
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Solid(),
        conflator = StrategyConflator.First(),
        values = { clickables },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.style] annotation type.
 */
public fun <A : AnyArguments, S> BaseStyleAnnotationProcessor(
    factory: (value: Int) -> S?
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { styles },
        factory = factory
    )

/**
 * `AnnotationProcessor` for [AnnotationTypes.decoration] annotation type.
 */
public fun <A : AnyArguments, S> BaseDecorationAnnotationProcessor(
    factory: (value: TextDecoration) -> S?,
): AnnotationProcessor<A, S> =
    object : AbstractAnnotationProcessor<TextDecoration, A, S>() {

        override val tokenizer: Tokenizer = Tokenizer.Solid()
        override val conflator: ValuesConfaltor<TextDecoration> = StrategyConflator.First()

        override fun parseValue(token: Token, arguments: A?): TextDecoration? =
            when (token) {
                Tokens.underline -> TextDecoration.Underline
                Tokens.strikethrough -> TextDecoration.Striketrhough
                else -> null
            }

        override fun A.getValues(): QualifiedList<TextDecoration> =
            decorations

        override fun makeSpan(value: TextDecoration): S? =
            factory(value)
    }

/**
 * `AnnotationProcessor` for [AnnotationTypes.size] annotation type.
 * [TextSize.value] units (pixels, SPs, etc.) are expected to be defined in concrete
 * implementation.
 */
public fun <A : AnyArguments, S> BaseSizeAnnotationProcessor(
    factory: (value: TextSize) -> S?
): AnnotationProcessor<A, S> =
    AnnotationProcessor(
        tokenizer = Tokenizer.Split().distinct(),
        conflator = StrategyConflator.First(),
        values = { sizes },
        factory = factory
    )

private object Tokens {
    val underline = Token("underline")
    val strikethrough = Token("strikethrough")
}