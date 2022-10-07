package com.mmolosay.stringannotations.args.values

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
 *
 * Each artifact of `StringAnnotations` library must resolve all generic types.
 */
public interface AnnotationValues<C : ClickOwner> {

    /**
     * Color integers.
     *
     * @see [androidx.annotation.ColorInt]
     */
    public val colors: QualifiedList<Int>

    /**
     * Clickable spans.
     */
    public val clickables: QualifiedList<out C>

    /**
     * Typeface style integers.
     *
     * @see [android.graphics.Typeface.BOLD]
     * @see [android.graphics.Typeface.ITALIC]
     * @see [android.graphics.Typeface.BOLD_ITALIC]
     */
    public val styles: QualifiedList<Int>

    /**
     * Absolute sizes.
     */
    public val sizes: QualifiedList<TextSize>
}