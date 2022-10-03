package com.mmolosay.stringannotations.internal.processor

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentSet
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
import com.mmolosay.stringannotations.processor.parser.ValueParser
import com.mmolosay.stringannotations.processor.token.Tokenizer
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

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

// TODO: try replacing defining types of AnnotaitonProcessor with instantiation of anon object
/**
 * `AnnotationProcessor` for "clickable" annotation type.
 */
public abstract class ClickableAnnotationProcessor<S> :
    ArgumentsSetAnnotationProcessor<ClickableSpan, S>() {

    override val tokenizer: Tokenizer = Tokenizer.Solid()
    override val valueParser: ValueParser<ClickableSpan>? = null
    override val conflator: ValuesConfaltor<ClickableSpan> = StrategyConflator.Single()

    override fun inferArguments(set: ArgumentSet?): Arguments<ClickableSpan>? =
        set?.clickables
}