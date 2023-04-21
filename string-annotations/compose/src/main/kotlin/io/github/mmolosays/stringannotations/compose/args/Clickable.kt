package io.github.mmolosays.stringannotations.compose.args

import io.github.mmolosays.stringannotations.args.types.ClickOwner

/**
 * Implementation of [ClickOwner] for Compose UI.
 *
 * Additionaly provides [annotation] string.
 * It will be used as value of `AnnotatedString` annotaion for this `Clickable`.
 */
public data class Clickable(
    public val annotation: String,
    override val action: ClickOwner.ClickAction,
) : ClickOwner