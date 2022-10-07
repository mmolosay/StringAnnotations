package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.qualified.QualifiedList

// TODO: use?
public interface ViewsArguments : Arguments {
    override val clickables: QualifiedList<Clickable>
}