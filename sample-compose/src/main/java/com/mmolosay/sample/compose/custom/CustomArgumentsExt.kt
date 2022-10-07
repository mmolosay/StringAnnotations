package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.emptyArguments

/**
 * Assembles [Arguments] with [CustomArguments] in declarative style.
 */
fun CustomArguments(
    base: Arguments = emptyArguments(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): Arguments =
    CustomAnnotationValuesBuilder().apply(scope).build(base)
