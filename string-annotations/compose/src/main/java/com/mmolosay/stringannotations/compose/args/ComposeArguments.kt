package com.mmolosay.stringannotations.compose.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

/**
 * Implementation of [Arguments] for Compose UI.
 */
public class ComposeArguments(
    override val values: ComposeAnnotationValues
) : Arguments