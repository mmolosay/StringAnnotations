package com.mmolosay.stringannotations.common.shared.args

import android.text.style.ClickableSpan
import com.mmolosay.stringannotations.common.shared.args.ArgumentsSet
import com.mmolosay.stringannotations.common.shared.args.MutableArguments

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
 * Internal mutable implementation of [ArgumentsSet].
 * Should not be used as explicit type.
 */
internal class MutableArgumentsSet(
    override val colors: MutableArguments<Int> = MutableArguments("color"),
    override val clickables: MutableArguments<ClickableSpan> = MutableArguments("clickable"),
    override val typefaceStyles: MutableArguments<Int> = MutableArguments("style"),
    override val absSizes: MutableArguments<Int> = MutableArguments("size-absolute")
) : ArgumentsSet