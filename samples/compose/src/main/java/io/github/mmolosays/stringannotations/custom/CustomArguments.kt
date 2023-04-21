package io.github.mmolosays.stringannotations.custom

import androidx.compose.ui.unit.TextUnit
import io.github.mmolosays.stringannotations.args.emptyArguments
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.compose.ComposeArguments

/**
 * Custom annotation values with [letterSpacings].
 *
 * We extend [ComposeArguments] in order to support all
 * out-of-the-box annotation types, while adding our custom ones.
 */
class CustomArguments(
    base: ComposeArguments = emptyArguments(),
    letterSpacings: List<TextUnit>,
) : ComposeArguments by base {
    val letterSpacings: QualifiedList<TextUnit> = QualifiedList("letter-spacing", letterSpacings)
}