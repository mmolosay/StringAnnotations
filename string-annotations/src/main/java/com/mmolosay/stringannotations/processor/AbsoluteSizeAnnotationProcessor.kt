package com.mmolosay.stringannotations.processor

import android.text.style.AbsoluteSizeSpan
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.AnnotationArguments
import com.mmolosay.stringannotations.processor.token.Tokenizer
import com.mmolosay.stringannotations.processor.parser.SizeUnitValueParser
import com.mmolosay.stringannotations.processor.parser.ValueParser
import com.mmolosay.stringannotations.processor.parser.arg.DefaultValueArgParser
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.parser.arg.ValueArgParser
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
 * `AnnotationProcessor` for "size-absolute" annotation type.
 */
internal class AbsoluteSizeAnnotationProcessor : BaseArgsAnnotationProcessor<Int>() {

    override val tokenizer: Tokenizer = Tokenizer.Split().distinct()
    override val valueParser: ValueParser<Int> = SizeUnitValueParser
    override val valueArgParser: ValueArgParser = DefaultValueArgParser
    override val conflator: ValuesConfaltor<Int> = StrategyConflator.Single()

    override fun inferValues(args: AnnotationArguments?): List<Int>? =
        args?.absSizes

    override fun makeSpan(value: Int): CharacterStyle =
        AbsoluteSizeSpan(value)
}