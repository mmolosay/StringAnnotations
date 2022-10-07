package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.qualified.QualifiedList

/**
 * Custom annotation values with [letterSpacings].
 *
 * We extend [Arguments] in order to support all
 * out-of-the-box annotation types, while adding our custom ones.
 */
class CustomArguments(
    base: Arguments,
    letterSpacings: List<TextUnit>,
) : Arguments by base {
    val letterSpacings: QualifiedList<TextUnit> = QualifiedList("letter-spacing", letterSpacings)
}