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
 * Set of [QualifiedCollection] of some annotated string.
 * They are used in places of annotation tag value placeholders,
 * like `<annotation color="$arg$color$0">text</annotation>`.
 *
 * `AnnotationArguments` is a great way to use values, which are tough (or completely impossible)
 * to parse from string, like click actions or dynamically computing text sizes.
 */
public interface ArgumentsSet {

    /**
     * Color integers.
     */
    public val colors: Arguments<Int>

    /**
     * Clickable spans.
     */
    public val clickables: Arguments<ClickableSpan>

    /**
     * Typeface style integers.
     */
    public val typefaceStyles: Arguments<Int>

    /**
     * Absolute sizes in pixels.
     */
    public val absSizes: Arguments<Int>

}