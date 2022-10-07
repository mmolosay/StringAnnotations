package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.qualified.MutableQualifiedList
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

class MutableCustomValues(
    values: ComposeAnnotationValues,
    override val customs: MutableQualifiedList<String> = MutableQualifiedList("custom")
) : CustomAnnotationValues, ComposeAnnotationValues by values