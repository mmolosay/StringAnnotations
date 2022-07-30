package com.example.stringannotations

import androidx.annotation.StringRes

/**
 * Represents string resource id, bound to its formatting arguments.
 */
class FormattedStringRes(
    @StringRes val res: Int,
    val args: Array<out String>
)