package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

class CustomAnnotationValuesBuilder(values: ComposeAnnotationValues) {

    private val values = MutableCustomValues(values)

    fun build(): CustomAnnotationValues =
        values

    fun custom(item: String) {
        values.customs.add(item)
    }

    fun customs(vararg items: String) {
        values.customs.addAll(items)
    }
}