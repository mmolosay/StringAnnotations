package io.github.mmolosays.stringannotations.args

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.args.types.ClickOwner
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize

/**
 * Default out-of-the-box implementation of [Arguments].
 *
 * Inherit from this component if you want to extend library's [Arguments] with your custom ones.
 */
public open class DefaultArguments<out C : ClickOwner>(
    clickables: List<C>,
    colors: List<Int>,
    decorations: List<TextDecoration>,
    sizes: List<TextSize>,
    styles: List<Int>,
) : Arguments<C> {

    public override val clickables: QualifiedList<C> =
        QualifiedList("clickable", clickables)

    public override val colors: QualifiedList<Int> =
        QualifiedList("color", colors)

    public override val decorations: QualifiedList<TextDecoration> =
        QualifiedList("decoration", decorations)

    public override val sizes: QualifiedList<TextSize> =
        QualifiedList("size", sizes)

    public override val styles: QualifiedList<Int> =
        QualifiedList("style", styles)
}