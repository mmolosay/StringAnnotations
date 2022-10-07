package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.args.ComposeArguments
import com.mmolosay.stringannotations.compose.args.emptyArguments

/**
 * Assembles [Arguments] with [CustomArguments] in declarative style.
 */
fun CustomArguments(
    base: ComposeArguments = emptyArguments(),
    scope: CustomArgumentsBuilder.() -> Unit
): CustomArguments =
    CustomArgumentsBuilder().apply(scope).build(base)
