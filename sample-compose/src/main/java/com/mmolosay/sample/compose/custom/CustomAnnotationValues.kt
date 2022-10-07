package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

interface CustomAnnotationValues : ComposeAnnotationValues {
    val customs: QualifiedList<String>
}