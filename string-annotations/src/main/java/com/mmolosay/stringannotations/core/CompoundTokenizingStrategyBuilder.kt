package com.mmolosay.stringannotations.core

/**
 * Providis convenient functionality for assembling complex [TokenizingStrategy].
 */
public open class CompoundTokenizingStrategyBuilder private constructor(
    private val strategy: TokenizingStrategy
) {

    /**
     * Each strategy tweaking method should add its [TokenizingArbiter] into this list.
     */
    protected var arbiters: MutableList<TokenizingArbiter> = mutableListOf()

    /**
     * Creates new instance of [CompoundTokenizingStrategyBuilder] with specified
     * [strategy] as its base one.
     */
    public fun of(strategy: TokenizingStrategy): CompoundTokenizingStrategyBuilder =
        CompoundTokenizingStrategyBuilder(strategy)

    /**
     * @see Sequence.distinct
     */
    public fun distinct(): CompoundTokenizingStrategyBuilder =
        apply {
            arbiters.add(
                TokenizingArbiter { it.distinct() }
            )
        }

    /**
     * Assembles [TokenizingStrategy].
     */
    public fun build(): TokenizingStrategy =
        TokenizingStrategy { value ->
            val initial = strategy.tokenize(value)
            arbiters.fold(initial) { tokens, arbiter ->
                arbiter.modify(tokens)
            }
        }

    /**
     * Modifies result of [TokenizingStrategy.tokenize].
     */
    protected fun interface TokenizingArbiter {
        public fun modify(tokens: Sequence<AnnotationTag.Token>): Sequence<AnnotationTag.Token>
    }
}