package com.mmolosay.stringannotations.parser

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import com.mmolosay.stringannotations.internal.Logger

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
 * Parses string annotation value of color type into color int.
 */
public object ColorValueParser : AnnotationValueParser<Int> {

    @ColorInt
    override fun parse(context: Context, value: String): Int? =
        parse(value)

    /**
     * Parses string [value] of any color attribute into color integer.
     *
     * Supported types of attribute value:
     * 1. color HEX RGB: `#RRGGBB`
     * 2. color HEX ARGS: `#AARRGGBB`
     * 2. common color name: `green`
     *
     * If valid color cannot be parsed, appropriate message will be logged.
     */
    @ColorInt
    public fun parse(value: String): Int? =
        try {
            Color.parseColor(value)
        } catch (e: IllegalArgumentException) {
            Logger.w("value=\"$value\" can not be parsed into valid color")
            null // return null, if attribute value is invalid
        }
}