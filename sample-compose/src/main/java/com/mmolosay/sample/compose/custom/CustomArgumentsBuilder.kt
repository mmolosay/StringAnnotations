package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.compose.args.ComposeArguments

/**
 * Builder for [CustomArguments].
 */
class CustomArgumentsBuilder {

    private val letterSpacings = mutableListOf<TextUnit>()

    fun build(base: ComposeArguments): CustomArguments =
        CustomArguments(base, letterSpacings)

    fun letterSpacing(item: TextUnit) {
        letterSpacings.add(item)
    }
}