package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.qualified.MutableQualifiedList
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

class CustomAnnotationValuesBuilder(values: ComposeAnnotationValues) {

    private val values = MutableCustomAnnotationValues(values)

    fun build(): CustomAnnotationValues =
        values

    fun custom(item: String) {
        values.customs.add(item)
    }

    fun customs(vararg items: String) {
        values.customs.addAll(items)
    }

    private class MutableCustomAnnotationValues(
        values: ComposeAnnotationValues,
        override val customs: MutableQualifiedList<String> = MutableQualifiedList("custom")
    ) : CustomAnnotationValues, ComposeAnnotationValues by values
}