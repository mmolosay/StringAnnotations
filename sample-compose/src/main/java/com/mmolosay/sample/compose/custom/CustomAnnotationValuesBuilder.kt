package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

class CustomAnnotationValuesBuilder(values: ComposeAnnotationValues) {

    private val args = MutableCustomValues(values)

    fun custom(item: String): CustomAnnotationValues =
        args.apply {
            args.customs.add(item)
        }

    fun customs(vararg items: String): CustomAnnotationValues =
        args.apply {
            args.customs.addAll(items)
        }
}