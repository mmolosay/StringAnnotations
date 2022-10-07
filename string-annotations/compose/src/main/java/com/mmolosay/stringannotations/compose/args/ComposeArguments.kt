package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.args.Arguments

/**
 * Definition of [Arguments] for Compose UI.
 */
public interface ComposeArguments : Arguments {
    override val clickables: Arguments.Clickables<Clickable>
}