package com.mmolosay.stringannotations.views.internal

import android.text.Spannable
import android.text.style.CharacterStyle

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
 * Processes spans (as [CharacterStyle]).
 */
internal object SpanProcessor {

    /**
     * Attaches specified [spans] to the [ranges] of [spannable] object accordingly.
     * All `null` spans will be skipped.
     */
    fun applySpans(
        spannable: Spannable,
        ranges: List<IntRange>,
        spans: List<CharacterStyle?>
    ) {
        for (i in spans.indices) {
            val span = spans.getOrNull(i) ?: break
            val range = ranges.getOrNull(i) ?: break
            applySpan(spannable, span, range)
        }
    }

    /**
     * Attaches specified [span] to the [range] of [spannable] object.
     */
    private fun applySpan(
        spannable: Spannable,
        span: CharacterStyle,
        range: IntRange
    ) {
        spannable.setSpan(span, range.first, range.last, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}