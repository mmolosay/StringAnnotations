package com.mmolosay.stringannotations.internal.processor

import android.text.Annotation
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.QualifiedList
import com.mmolosay.stringannotations.processor.AbstractAnnotationProcessor
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

/**
 * `AnnotationProcessor` for "style" annotation type.
 */
public abstract class StyleAnnotationProcessor<S> :
    AbstractAnnotationProcessor<Token, S>() {

    override val tokenizer: Tokenizer = Tokenizer.Solid()
    override val conflator: ValuesConfaltor<Token> = StrategyConflator.Single()

    protected abstract val typefaceStyleAnnotationProcessor: TypefaceStyleAnnotationProcessor<S>

    override fun parseAnnotation(
        annotation: Annotation,
        arguments: Arguments?
    ): S? =
        super.parseAnnotation(annotation, arguments)
            ?: typefaceStyleAnnotationProcessor.parseAnnotation(annotation, arguments)

    override fun Arguments.getValues(): QualifiedList<Token>? =
        null

    override fun makeSpan(value: Token): S? =
        when (value) {
            tokenUnderline -> makeUnderlineSpan()
            tokenStrikethrough -> makeStrikethroughSpan()
            else -> null
        }

    protected abstract fun makeUnderlineSpan(): S?

    protected abstract fun makeStrikethroughSpan(): S?

    private companion object {
        val tokenUnderline = Token("underline")
        val tokenStrikethrough = Token("strikethrough")
    }
}