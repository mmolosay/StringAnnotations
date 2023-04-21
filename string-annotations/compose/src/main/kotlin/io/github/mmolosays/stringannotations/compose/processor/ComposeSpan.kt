package io.github.mmolosays.stringannotations.compose.processor

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import io.github.mmolosays.stringannotations.compose.args.Clickable

/**
 * Aggregation of possible "spans" for Compose UI.
 */
public class ComposeSpan private constructor(
    public val spanStyle: SpanStyle? = null,
    public val paragraphStyle: ParagraphStyle? = null,
    public val clickable: Clickable? = null
) {

    public operator fun component1(): SpanStyle? =
        spanStyle

    public operator fun component2(): ParagraphStyle? =
        paragraphStyle

    public operator fun component3(): Clickable? =
        clickable

    public companion object {

        /**
         * Creates [ComposeSpan] with specified [SpanStyle].
         */
        public fun of(type: SpanStyle): ComposeSpan =
            ComposeSpan(spanStyle = type)

        /**
         * Creates [ComposeSpan] with specified [ParagraphStyle].
         */
        public fun of(type: ParagraphStyle): ComposeSpan =
            ComposeSpan(paragraphStyle = type)

        /**
         * Creates [ComposeSpan] with specified [Clickable].
         */
        public fun of(type: Clickable): ComposeSpan =
            ComposeSpan(clickable = type)
    }
}