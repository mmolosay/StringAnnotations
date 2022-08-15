package com.mmolosay.stringannotations.args

import android.text.style.ClickableSpan

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
 * Immutable collection of runtime value arguments of some annotated string.
 * They are used in places of annotation tag value placeholders, like `<annotation color="arg$0">text</annotation>`.
 *
 * Runtime value arguments are a great way to use values, which are tough (or completely impossible)
 * to parse from strings, like click actions or dynamically computing text sizes.
 *
 * @see [com.mmolosay.stringannotations.processor.DefaultAnnotationValueProcessor.parsePlaceholderAs]
 */
public interface ValueArgs {

    /**
     * Color integers.
     */
    public val colors: List<Int>

    /**
     * Clickable spans.
     */
    public val clickables: List<ClickableSpan>

    /**
     * Typeface style integers.
     */
    public val typefaceStyles: List<Int>

    /**
     * Absolute sizes in pixels.
     */
    public val absSizes: List<Int>
}
