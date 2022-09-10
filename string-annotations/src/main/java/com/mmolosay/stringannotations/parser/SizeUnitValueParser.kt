package com.mmolosay.stringannotations.parser

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.annotation.Px
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
 * Parses string annotation value of size type into pixel size.
 */
public object SizeUnitValueParser : AnnotationValueParser<Int> {

    override fun parse(context: Context, value: String): Int? =
        parse(value, context.resources.displayMetrics)

    /**
     * Parses [value] of format `"{NUMBER_AMOUNT}{UNIT}"` into pixel size.
     */
    @Px
    public fun parse(
        value: String,
        metrics: DisplayMetrics
    ): Int? {
        val pair = parse(value) ?: return null
        return parse(pair.first, pair.second, metrics)
    }

    /**
     * Splites complex size [value], specified in units into pair of float size and unit label, i.e.
     * `"18.56sp"` -> `{18.56f, "sp"}`.
     */
    private fun parse(value: String): Pair<Float, String>? {
        val unit = value.takeLastWhile { it.isLetter() }
        val size = value
            .substring(0, value.length - unit.length)
            .toFloatOrNull()
            ?: return null
        return Pair(size, unit)
    }

    /**
     * Parses [size], specified in [unit] into pixel equivalent size.
     */
    @Px
    private fun parse(
        size: Float,
        unit: String,
        metrics: DisplayMetrics
    ): Int? =
        when (unit) {
            UNIT_LABEL_PX, "" -> size.toInt() // already pixels, but just float
            UNIT_LABEL_SP -> parseSizeUnit(size, TypedValue.COMPLEX_UNIT_SP, metrics)
            UNIT_LABEL_DP -> parseSizeUnit(size, TypedValue.COMPLEX_UNIT_DIP, metrics)
            else -> {
                Logger.w("Unknown size unit \"$unit\"")
                null
            }
        }

    /**
     * Converts [size], specified in [unit] into pixel equivalent size.
     */
    @Px
    private fun parseSizeUnit(
        size: Float,
        unit: Int,
        metrics: DisplayMetrics
    ): Int =
        TypedValue.applyDimension(
            /*unit*/ unit,
            /*value*/ size,
            /*metrics*/ metrics
        ).toInt()

    private const val UNIT_LABEL_PX = "px"
    private const val UNIT_LABEL_SP = "sp"
    private const val UNIT_LABEL_DP = "dp"
}