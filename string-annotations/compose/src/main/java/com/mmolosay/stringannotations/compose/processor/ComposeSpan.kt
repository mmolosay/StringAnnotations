package com.mmolosay.stringannotations.compose.processor

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

/**
 * Aggregation of possible "spans" for Compose UI.
 */
public class ComposeSpan private constructor(
    public val spanStyle: SpanStyle? = null,
    public val paragraphStyle: ParagraphStyle? = null,
    public val clickableSpan: ClickableSpan? = null
) {

    public operator fun component1(): SpanStyle? =
        spanStyle

    public operator fun component2(): ParagraphStyle? =
        paragraphStyle

    public operator fun component3(): ClickableSpan? =
        clickableSpan

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
         * Creates [ComposeSpan] with specified [ClickableSpan].
         */
        public fun of(type: ClickableSpan): ComposeSpan =
            ComposeSpan(clickableSpan = type)
    }
}