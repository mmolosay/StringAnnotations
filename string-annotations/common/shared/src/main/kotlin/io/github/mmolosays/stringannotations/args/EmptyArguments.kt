package io.github.mmolosays.stringannotations.args

import io.github.mmolosays.stringannotations.args.types.ClickOwner

/**
 * Internal implementation of empty [Arguments].
 * Should not be used as explicit type.
 */
private object EmptyArguments : DefaultArguments<Nothing>(
    clickables = emptyList(),
    colors = emptyList(),
    decorations = emptyList(),
    sizes = emptyList(),
    styles = emptyList(),
)

/**
 * Returns instance of empty [Arguments].
 */
public fun <C : ClickOwner> emptyArguments(): Arguments<C> =
    EmptyArguments