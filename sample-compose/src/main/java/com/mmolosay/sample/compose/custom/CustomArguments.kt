package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.compose.args.ComposeArguments

/**
 * Custom annotation values with [letterSpacings].
 *
 * We extend [ComposeArguments] in order to support all
 * out-of-the-box annotation types, while adding our custom ones.
 */
class CustomArguments(
    base: ComposeArguments,
    letterSpacings: List<TextUnit>,
) : ComposeArguments by base {
    val letterSpacings: QualifiedList<TextUnit> = QualifiedList("letter-spacing", letterSpacings)
}