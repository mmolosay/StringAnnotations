package com.mmolosay.stringannotations.args

import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextSize

/*
 * Copyright 2022 Mikhail Malasai
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
public interface Arguments<C : ClickOwner> {

    public val colors: Colors
    public val clickables: Clickables<C>
    public val styles: Styles
    public val sizes: Sizes

    /**
     * Color integers.
     *
     * @see [androidx.annotation.ColorInt]
     */
    public class Colors(list: List<Int>) : QualifiedList<Int>("color", list)

    /**
     * Clickable spans.
     */
    public class Clickables<C>(list: List<C>) : QualifiedList<C>("clickable", list)

    /**
     * Typeface style integers.
     *
     * @see [android.graphics.Typeface.BOLD]
     * @see [android.graphics.Typeface.ITALIC]
     * @see [android.graphics.Typeface.BOLD_ITALIC]
     */
    public class Styles(list: List<Int>) : QualifiedList<Int>("style", list)

    /**
     * Absolute sizes.
     */
    public class Sizes(list: List<TextSize>) : QualifiedList<TextSize>("size", list)

}