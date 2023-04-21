package io.github.mmolosays.stringannotations.args

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.args.types.ClickOwner
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize

/*
 * Copyright 2023 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Immutable set of [QualifiedList]s with values, destined for some annotated string.
 * Contains values for all annotation types, supported out-of-the-box.
 */
public interface Arguments<out C : ClickOwner> {

    // please, preserve alphabetical order
    public val clickables: Clickables<C>
    public val colors: Colors
    public val decorations: Decorations
    public val sizes: Sizes
    public val styles: Styles

    /**
     * Clickable spans.
     */
    public class Clickables<out C>(list: List<C>) :
        QualifiedList<C>("clickable", list)

    /**
     * Color integers.
     *
     * @see [androidx.annotation.ColorInt]
     */
    public class Colors(list: List<Int>) :
        QualifiedList<Int>("color", list)

    /**
     * Text decorations.
     *
     * @see [TextDecoration]
     */
    public class Decorations(list: List<TextDecoration>) :
        QualifiedList<TextDecoration>("decoration", list)

    /**
     * Absolute text sizes.
     *
     * @see [TextSize]
     */
    public class Sizes(list: List<TextSize>) :
        QualifiedList<TextSize>("size", list)

    /**
     * Typeface style integers.
     *
     * @see [android.graphics.Typeface.BOLD]
     * @see [android.graphics.Typeface.ITALIC]
     * @see [android.graphics.Typeface.BOLD_ITALIC]
     */
    public class Styles(list: List<Int>) :
        QualifiedList<Int>("style", list)

}