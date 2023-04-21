package io.github.mmolosays.stringannotations.custom

import androidx.compose.ui.text.SpanStyle
import io.github.mmolosays.stringannotations.compose.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.processor.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.processor.ComposeSpan
import io.github.mmolosays.stringannotations.compose.processor.MasterAnnotationProcessor
import io.github.mmolosays.stringannotations.processor.confaltor.StrategyConflator
import io.github.mmolosays.stringannotations.processor.token.Tokenizer

/**
 * Overload of [MasterAnnotationProcessor] with custom "letter-spacing" annotation type.
 */
class CustomMasterAnnotationProcessor : MasterAnnotationProcessor() {

    override fun createAnnotationProcessor(type: String): ComposeAnnotationProcessor? =
        when (type) {
            "letter-spacing" -> createLetterSpacingAnnotationProcessor()
            else -> super.createAnnotationProcessor(type)
        }

    private fun createLetterSpacingAnnotationProcessor(): ComposeAnnotationProcessor =
        ComposeAnnotationProcessor(
            tokenizer = Tokenizer.Solid(),
            conflator = StrategyConflator.First(),
            values = { (this as? CustomArguments)?.letterSpacings }
        ) {
            ComposeSpan.of(SpanStyle(letterSpacing = it))
        }
}