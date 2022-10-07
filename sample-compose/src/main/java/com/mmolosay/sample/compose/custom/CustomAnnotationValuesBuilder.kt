package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

/**
 * Builder for [CustomAnnotationValues].
 */
class CustomAnnotationValuesBuilder {

    private val letterSpacings = mutableListOf<TextUnit>()

    fun build(base: ComposeAnnotationValues): CustomAnnotationValues =
        CustomAnnotationValues(base, letterSpacings)

    fun letterSpacing(item: TextUnit) {
        letterSpacings.add(item)
    }
}