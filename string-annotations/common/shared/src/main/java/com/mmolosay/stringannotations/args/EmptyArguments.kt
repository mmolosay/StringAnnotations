package com.mmolosay.stringannotations.args

/**
 * Internal implementation of empty [Arguments].
 * Should not be used as explicit type.
 */
internal object EmptyArguments : Arguments<Nothing> {
    override val colors = Arguments.Colors(emptyList())
    override val clickables = Arguments.Clickables<Nothing>(emptyList())
    override val styles = Arguments.Styles(emptyList())
    override val sizes = Arguments.Sizes(emptyList())
}