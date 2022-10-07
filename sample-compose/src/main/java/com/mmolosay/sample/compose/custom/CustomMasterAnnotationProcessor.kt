package com.mmolosay.sample.compose.custom

import androidx.compose.ui.text.SpanStyle
import com.mmolosay.stringannotations.compose.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.compose.processor.MasterAnnotationProcessor
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.token.Tokenizer

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
        AnnotationProcessor(
            tokenizer = Tokenizer.Solid(),
            conflator = StrategyConflator.First(),
            values = { (values as? CustomAnnotationValues)?.letterSpacings }
        ) {
            ComposeSpan.of(SpanStyle(letterSpacing = it))
        }
}