package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.emptyArguments

/**
 * Assembles [Arguments] with [CustomArguments] in declarative style.
 */
fun CustomArguments(
    base: Arguments = emptyArguments(),
    scope: CustomArgumentsBuilder.() -> Unit
): Arguments =
    CustomArgumentsBuilder().apply(scope).build(base)
