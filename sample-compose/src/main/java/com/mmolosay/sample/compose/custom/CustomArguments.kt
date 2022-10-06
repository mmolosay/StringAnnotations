package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.compose.ComposeArguments

interface CustomArguments : ComposeArguments {

    val customs: QualifiedList<String>
}