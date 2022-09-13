package com.mmolosay.stringannotations.core

/**
 * Specifies a way of parsing string annotation's tag value into some individual, atomic tokens,
 * being used to determine appropriate span values.
 */
public fun interface TokenizingStrategy {

    public fun tokenize(value: String): Sequence<AnnotationTag.Token>

    public companion object {

        /**
         * Employs value as single token.
         */
        public fun Solid(): TokenizingStrategy =
            TokenizingStrategy { value ->
                sequenceOf(AnnotationTag.Token(value))
            }

        /**
         * Splits value into tokens, using specified [delimiter].
         */
        public fun Split(delimiter: String): TokenizingStrategy =
            TokenizingStrategy { value ->
                value
                    .split(delimiter)
                    .map { AnnotationTag.Token(it) }
                    .asSequence()
            }
    }
}

