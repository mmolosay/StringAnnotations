package com.mmolosay.stringannotations.args

import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

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
 * Internal mutable implementation of [Arguments].
 * Should not be used as explicit type.
 */
internal class MutableArguments(
    override val colors: MutableQualifiedList<Int> = MutableQualifiedList("color"),
    override val clickables: MutableQualifiedList<ClickableSpan> = MutableQualifiedList("clickable"),
    override val typefaceStyles: MutableQualifiedList<Int> = MutableQualifiedList("style"),
    override val absSizes: MutableQualifiedList<Float> = MutableQualifiedList("size-absolute")
) : Arguments