package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.qualified.QualifiedList
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextSize
import com.mmolosay.stringannotations.args.values.AnnotationValues

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
 * Builder for [AnnotationValues].
 */
internal fun <C : ClickOwner> AnnotationValues(
    colors: List<Int>,
    clickables: List<C>,
    styles: List<Int>,
    sizes: List<TextSize>,
): AnnotationValues =
    object : AnnotationValues {
        override val colors = QualifiedList("color", colors)
        override val clickables: QualifiedList<out ClickOwner> = QualifiedList("clickable", clickables)
        override val styles = QualifiedList("style", styles)
        override val sizes = QualifiedList("size", sizes)
    }