package com.mmolosay.stringannotations.processor.token

/**
 * Specifies a way of parsing string annotation's tag value into some individual, atomic tokens,
 * being used to determine appropriate span values.
 */
public fun interface Tokenizer {

    public fun tokenize(value: String): Sequence<Token>

    public companion object {

        /**
         * Employs value as single token.
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

