package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.AnnotationValues
import com.mmolosay.stringannotations.args.qualified.MutableQualifiedList
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
 * Implementation of mutable [AnnotationValues].
 * Should not be used as explicit type.
 */
internal class MutableAnnotationValues<C : ClickOwner>(
    override val colors: MutableQualifiedList<Int> = MutableQualifiedList("color"),
    override val clickables: MutableQualifiedList<C> = MutableQualifiedList("clickable"),
    override val styles: MutableQualifiedList<Int> = MutableQualifiedList("style"),
    override val sizes: MutableQualifiedList<TextSize> = MutableQualifiedList("size")
) : AnnotationValues<C>