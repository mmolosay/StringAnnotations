package com.mmolosay.stringannotations.args

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
 * Immutable set of [QualifiedList]s of some annotated string.
 *
 * `Arguments` are used to provide data to annotations, that will result in their
 * final appearance and/or behviour.
 *
 * Each artifact of `StringAnnotations` library must provide their own implementation and
 * resolve all generic types.
 * `Arguments` and their implementations should be extended in order to provide data to
 * custom annotation types.
 */
public interface Arguments<C : ClickOwner> {

    /**
     * Color integers.
     */
    public val colors: QualifiedList<Int> // TODO: replace with ColorsList : QualifiedList<Int>("colors") ?

    /**
     * Clickable spans.
     */
    public val clickables: QualifiedList<C>

    /**
     * Typeface style integers.
     */
    public val typefaceStyles: QualifiedList<Int>

    /**
     * Absolute sizes.
     */
    public val absSizes: QualifiedList<TextSize>

}
