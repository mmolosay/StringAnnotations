package io.github.mmolosays.stringannotations.args

import io.github.mmolosays.stringannotations.args.types.ClickOwner

/**
 * Internal implementation of empty [Arguments].
 * Should not be used as explicit type.
 */
internal object EmptyArguments : Arguments<Nothing> {
    override val clickables = Arguments.Clickables<Nothing>(emptyList())
    override val colors = Arguments.Colors(emptyList())
    override val decorations = Arguments.Decorations(emptyList())
    override val sizes = Arguments.Sizes(emptyList())
    override val styles = Arguments.Styles(emptyList())
}

/**
 * Returns instance of empty [Arguments].
 */
public fun <C : ClickOwner> emptyArguments(): Arguments<C> =
    EmptyArguments