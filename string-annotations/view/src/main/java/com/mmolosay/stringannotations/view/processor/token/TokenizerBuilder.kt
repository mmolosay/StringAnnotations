package com.mmolosay.stringannotations.view.processor.token

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
 * Providis convenient functionality for assembling complex [Tokenizer].
 */
public open class TokenizerBuilder(private val tokenizer: Tokenizer) : Tokenizer {

    /**
     * Each tokenizing behaviour tweaking method should add its [TokeinzingArbiter] into this list.
     */
    protected var arbiters: MutableList<TokeinzingArbiter> = mutableListOf()

    override fun tokenize(value: String): Sequence<Token> {
        val initial = tokenizer.tokenize(value)
        return arbiters.fold(initial) { tokens, arbiter ->
            arbiter.modify(tokens)
        }
    }

    /**
     * @see Sequence.distinct
     */
    public fun distinct(): TokenizerBuilder =
        apply {
            arbiters.add(
                TokeinzingArbiter { it.distinct() }
            )
        }

    /**
     * Modifies result of [Tokenizer.tokenize].
     */
    protected fun interface TokeinzingArbiter {
        public fun modify(tokens: Sequence<Token>): Sequence<Token>
    }
}