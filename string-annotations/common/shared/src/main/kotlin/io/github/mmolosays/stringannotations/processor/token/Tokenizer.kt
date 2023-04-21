package io.github.mmolosays.stringannotations.processor.token

/*
 * Copyright 2023 Mikhail Malasai
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
 * Disassembles annotation's tag value into sequence of [Token]s.
 */
public fun interface Tokenizer {

    public fun tokenize(value: String): Sequence<Token>

    public companion object {

        /**
         * Employs whole value as single token.
         */
        public fun Solid(): TokenizerBuilder =
            TokenizerBuilder { value ->
                sequenceOf(Token(value))
            }

        /**
         * Splits value into tokens, using specified [delimiter].
         */
        public fun Split(delimiter: String = "|"): TokenizerBuilder =
            TokenizerBuilder { value ->
                value
                    .split(delimiter)
                    .map { Token(it) }
                    .asSequence()
            }
    }
}

