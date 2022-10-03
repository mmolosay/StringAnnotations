package com.mmolosay.stringannotations.internal.processor

import android.graphics.Typeface
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.QualifiedList
import com.mmolosay.stringannotations.processor.BaseAnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.confaltor.ValuesConfaltor
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
 * `AnnotationProcessor` for typeface style annotation type.
 */
public abstract class TypefaceStyleAnnotationProcessor<S> :
    BaseAnnotationProcessor<Int, S>() {

    override val tokenizer: Tokenizer = Tokenizer.Split().distinct()
    override val conflator: ValuesConfaltor<Int> = StrategyConflator.All(::reduceTypefaceStyles)

    override fun Arguments.getValues(): QualifiedList<Int>? =
        typefaceStyles

    private fun reduceTypefaceStyles(styles: Collection<Int>): Int? {
        if (styles.isEmpty()) return null
        if (styles.size == 1) return styles.first()
        return if (styles.contains(Typeface.NORMAL)) {
            reduceTypefaceStyles(styles - Typeface.NORMAL)
        } else {
            Typeface.BOLD_ITALIC
        }
    }
}