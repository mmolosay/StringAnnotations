package com.mmolosay.stringannotations.args.values

import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextSize

/**
 * Internal implementation of empty [AnnotationValues].
 * Should not be used as explicit type.
 */
internal object EmptyAnnotationValues : AnnotationValues {
    override val colors: QualifiedList<Int> = QualifiedList("")
    override val clickables: QualifiedList<out ClickOwner> = QualifiedList("")
    override val styles: QualifiedList<Int> = QualifiedList("")
    override val sizes: QualifiedList<TextSize> = QualifiedList("")
}