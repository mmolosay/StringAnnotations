package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.Spanned

/*
 * Copyright 2023 Mikhail Malasai
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
 * Processes [Annotation]s.
 */
internal object AnnotationSpanProcessor {

    /**
     * Retrieves [annotation]'s start and end positions in terms of specified [string].
     * If [annotation] is `null`, then we assume that is a top-most root, and return full [string]
     * range.
     */
    fun parseAnnotationRange(
        string: Spanned,
        annotation: Annotation?,
    ): IntRange {
        annotation ?: return 0..string.length
        val start = string.getSpanStart(annotation)
        val end = string.getSpanEnd(annotation)
        require(start != -1 && end != -1) { "Specified annotation is not attached to this Spanned" }
        return (start..end)
    }

    /**
     * Variant of [parseAnnotationRange] that works with [annotations] array.
     */
    fun parseAnnotationRanges(
        string: Spanned,
        annotations: Array<out Annotation>,
    ): List<IntRange> =
        annotations.map { parseAnnotationRange(string, it) }

    /**
     * Parses specified [annotations] of [string] into list of [PlacedAnnotation].
     */
    fun parseStringAnnotations(
        string: Spanned,
        annotations: Array<out Annotation>,
    ): List<PlacedAnnotation> =
        annotations.mapIndexed { index, annotation ->
            val range = parseAnnotationRange(string, annotation)
            if (range.first == -1 || range.last == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            PlacedAnnotation(annotation, range.first, range.last, index)
        }
}