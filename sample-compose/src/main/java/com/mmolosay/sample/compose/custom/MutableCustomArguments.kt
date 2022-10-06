package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.qualified.MutableQualifiedList
import com.mmolosay.stringannotations.compose.ComposeArguments

class MutableCustomArguments(
    private val arguments: ComposeArguments,
    override val customs: MutableQualifiedList<String> = MutableQualifiedList("custom")
) : CustomArguments, ComposeArguments by arguments