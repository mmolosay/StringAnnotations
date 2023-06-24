package io.github.mmolosays.stringannotations

import androidx.compose.ui.text.SpanStyle
import io.github.mmolosays.stringannotations.compose.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.processor.ComposeAnnotationProcessor
import io.github.mmolosays.stringannotations.compose.processor.ComposeSpan
import io.github.mmolosays.stringannotations.compose.processor.MasterAnnotationProcessor

/**
 * Overload of [MasterAnnotationProcessor] with custom "letter-spacing" annotation type.
 */
class MyMasterAnnotationProcessor : MasterAnnotationProcessor() {

    override fun createAnnotationProcessor(type: String): ComposeAnnotationProcessor? =
        when (type) {
            "letter-spacing" -> createLetterSpacingAnnotationProcessor()
            else -> super.createAnnotationProcessor(type)
        }

    private fun createLetterSpacingAnnotationProcessor(): ComposeAnnotationProcessor =
        ComposeAnnotationProcessor(
            parser = defaultValueParser,
            values = { (this as? MyArguments)?.letterSpacings },
        ) {
            ComposeSpan.of(SpanStyle(letterSpacing = it))
        }
}