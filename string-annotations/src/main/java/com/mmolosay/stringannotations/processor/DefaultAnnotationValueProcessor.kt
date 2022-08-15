package com.mmolosay.stringannotations.processor

import android.text.style.ClickableSpan
import android.util.DisplayMetrics

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
 * Processes values of string annotation tags.
 * Contains methods for parsing all annotaiton types, supported by [DefaultAnnotationProcessor].
 *
 */
public sealed interface DefaultAnnotationValueProcessor {

    /**
     * Splits annotation value of type `"value1[value2][value3][...]"` into list of
     * separate atomic values.
     */
    public fun split(values: String): List<String>

    /**
     * Parses specified [value] as color integer, using [args] for resolving placeholder.
     */
    public fun parseColor(value: String, args: List<Int>): Int?

    /**
     * Parses specified [value] as typeface style integers, using [args] for resolving placeholder.
     */
    public fun parseTypefaceStyle(value: String, args: List<Int>): Int?

    /**
     * Parses specified [placeholder], using [args] for resolving corresponding span attribute.
     */
    public fun parseClickable(placeholder: String, args: List<ClickableSpan>): ClickableSpan?

    /**
     * Parses specified [value] as pixel size, using [args] for resolving placeholder.
     * Uses [metrics] for converting units into pixel equivalent.
     */
    public fun parseAbsoluteSize(value: String, args: List<Int>, metrics: DisplayMetrics): Int?
}