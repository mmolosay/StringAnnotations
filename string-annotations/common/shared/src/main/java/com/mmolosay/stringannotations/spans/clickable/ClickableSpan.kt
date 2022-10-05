package com.mmolosay.stringannotations.spans.clickable

import android.graphics.Color
import androidx.annotation.ColorInt

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
 * Span with click action.
 */
public data class ClickableSpan(
    val appearance: Appearance = Appearance(),
    val action: ClickAction
) {

    public fun interface ClickAction {
        public fun click()
    }

    /**
     * Appearance properties of clickable text.
     */
    public data class Appearance(

        /**
         * Defines, whether text should be underlined or not.
         */
        val underlineText: Boolean = false,

        /**
         * Color of text.
         */
        @ColorInt val textColor: Int = Color.BLUE
    ) {
        public companion object
    }
}