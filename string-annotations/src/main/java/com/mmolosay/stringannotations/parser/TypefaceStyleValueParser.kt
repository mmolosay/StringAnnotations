package com.mmolosay.stringannotations.parser

import android.content.Context
import android.graphics.Typeface

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
 * Parses string annotation value of typeface style type into typeface style int.
 */
public object TypefaceStyleValueParser : AnnotationValueParser<Int> {

    override fun parse(context: Context, value: String): Int? =
        parse(value)

    /**
     * Parses string [value] of some typeface style into typeface style integer.
     *
     * @return parsed typeface style integer or `null`, if there's no matches.
     */
    public fun parse(value: String): Int? =
        when (value) {
            TYPEFACE_STYLE_BOLD -> Typeface.BOLD
            TYPEFACE_STYLE_ITALIC -> Typeface.ITALIC
            TYPEFACE_STYLE_NORMAL -> Typeface.NORMAL
            else -> null // gibberish
        }

    /**
     * Reduces specified typeface [styles] into single one.
     * All [Typeface.NORMAL] would be reduced, if there any other ones.
     *
     * ```
     * Samples:
     * [bold] -> bold
     * [italic, normal] -> italic
     * [normal, bold, italic] -> bold_italic
     * ```
     */
    public fun reduceTypefaceStyles(styles: List<Int>): Int? {
        if (styles.isEmpty()) return null
        if (styles.size == 1) return styles.first()
        return if (styles.contains(Typeface.NORMAL)) {
            reduceTypefaceStyles(styles - Typeface.NORMAL)
        } else {
            Typeface.BOLD_ITALIC
        }
    }

    private const val TYPEFACE_STYLE_BOLD = "bold"
    private const val TYPEFACE_STYLE_ITALIC = "italic"
    private const val TYPEFACE_STYLE_NORMAL = "normal"
}