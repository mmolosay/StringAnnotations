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

    /**
     * Clickable spans.
     */
    public val clickables: QualifiedList<C>

    /**
     * Color integers, like 0xFFFFFFFF.
     *
     * @see [android.graphics.Color]
     */
    public val colors: QualifiedList<Int>

    /**
     * Text decorations.
     *
     * @see [TextDecoration]
     */
    public val decorations: QualifiedList<TextDecoration>

    /**
     * Absolute text sizes.
     *
     * @see [TextSize]
     */
    public val sizes: QualifiedList<TextSize>


    /**
     * Typeface style integers.
     *
     * @see [android.graphics.Typeface.BOLD]
     * @see [android.graphics.Typeface.ITALIC]
     * @see [android.graphics.Typeface.BOLD_ITALIC]
     */
    public val styles: QualifiedList<Int>
}