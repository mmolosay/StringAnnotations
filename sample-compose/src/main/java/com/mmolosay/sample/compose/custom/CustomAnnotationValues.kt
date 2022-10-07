package com.mmolosay.sample.compose.custom

import androidx.compose.ui.unit.TextUnit
import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.values.AnnotationValues

/**
 * Custom annotation values with [letterSpacings].
 *
 * We extend [AnnotationValues] in order to support all
 * out-of-the-box annotation types, while adding our custom ones.
 */
class CustomAnnotationValues(
    base: AnnotationValues,
    letterSpacings: List<TextUnit>,
) : AnnotationValues by base {
    val letterSpacings: QualifiedList<TextUnit> = QualifiedList("letter-spacing", letterSpacings)
}