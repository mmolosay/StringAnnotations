package com.mmolosay.stringannotations.processor.token

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