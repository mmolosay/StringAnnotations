package com.mmolosay.stringannotations.parser

import android.graphics.Color
import androidx.annotation.ColorInt
import com.mmolosay.stringannotations.core.Logger

/**
 * Parses string annotation value of color type into color int.
 */
public object ColorValueParser {

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