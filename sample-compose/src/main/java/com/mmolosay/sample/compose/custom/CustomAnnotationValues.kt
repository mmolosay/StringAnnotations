package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

/**
 * Custom annotation values with [letterSpacings].
 *
 * We extend [ComposeAnnotationValues] in order to support all
 * out-of-the-box annotation types, while adding our custom ones.
 */
class CustomAnnotationValues(
    base: ComposeAnnotationValues,
    letterSpacings: List<TextUnit>,
) : ComposeAnnotationValues by base {
    val letterSpacings: QualifiedList<TextUnit> = QualifiedList("letter-spacing", letterSpacings)
}