package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.processor.MasterAnnotationProcessor
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.confaltor.StrategyConflator
import com.mmolosay.stringannotations.processor.token.Tokenizer

class CustomMasterAnnotationProcessor : MasterAnnotationProcessor() {

    override fun createAnnotationProcessor(type: String): ComposeAnnotationProcessor? =
        when (type) {
            "custom" -> createCustomAnnotationProcessor()
            else -> super.createAnnotationProcessor(type)
        }

    private fun createCustomAnnotationProcessor(): ComposeAnnotationProcessor =
        AnnotationProcessor(
            tokenizer = Tokenizer.Solid(),
            conflator = StrategyConflator.First(),
            values = { (this as? CustomArguments)?.customs }
        ) {
            null // TODO: assemble your ComposeSpan here
        }
}