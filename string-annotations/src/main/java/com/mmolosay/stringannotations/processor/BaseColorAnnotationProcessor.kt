package com.mmolosay.stringannotations.processor

import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.core.Tokenizer
import com.mmolosay.stringannotations.parser.ColorTokenParser
import com.mmolosay.stringannotations.parser.TokenParser
import com.mmolosay.stringannotations.core.DefaultValueArgParser
import com.mmolosay.stringannotations.core.Evaluator
import com.mmolosay.stringannotations.core.ValueArgParser

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
 * `AnnotationProcessor` for color annotation type.
 */
public abstract class BaseColorAnnotationProcessor : BaseAnnotationProcessor<Int>() {

    override val tokenizer: Tokenizer = Tokenizer.Split().distinct()
    override val tokenParser: TokenParser<Int> = ColorTokenParser
    override val valueArgParser: ValueArgParser = DefaultValueArgParser
    override val evaluator: Evaluator<Int> = Evaluator.Single()

    final override fun inferArgs(args: ValueArgs?): List<Int>? =
        args?.colors
}