package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.emptyArguments
import com.mmolosay.stringannotations.compose.ComposeArguments

/**
 * Assembles [Arguments] with [CustomArguments] in declarative style.
 */
fun CustomArguments(
    base: ComposeArguments = emptyArguments(),
    scope: CustomArgumentsBuilder.() -> Unit
): CustomArguments =
    CustomArgumentsBuilder().apply(scope).build(base)
