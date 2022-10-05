package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.spans.clickable.ClickOwner

/**
 * Implementation of [ClickOwner] for Compose UI.
 *
 * Additionaly provides [annotation] string. It will be used as annotation of `AnnotatedString`.
 */
public data class Clickable(
    public val annotation: String,
    override val action: ClickOwner.ClickAction
) : ClickOwner