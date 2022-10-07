package com.mmolosay.stringannotations.views.args

import com.mmolosay.stringannotations.args.Arguments

/**
 * Definition of [Arguments] for Android Views UI.
 */
public interface ViewsArguments : Arguments {
    override val clickables: Arguments.Clickables<Clickable>
}