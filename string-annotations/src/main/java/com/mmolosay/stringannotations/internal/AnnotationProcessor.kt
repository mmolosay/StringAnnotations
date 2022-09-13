package com.mmolosay.stringannotations.internal

import android.content.Context
import android.text.Annotation
import android.text.Spanned
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.ValueArgs
import com.mmolosay.stringannotations.core.AnnotationProcessorResolver

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
 * Processes [Annotation]s.
 */
internal object AnnotationProcessor {

    /**
     * Parses specified [annotations] of [spanned] into list of [StringAnnotation].
     */
    fun parseStringAnnotations(
        spanned: Spanned,
        annotations: Array<out Annotation>
    ): List<StringAnnotation> =
        annotations.mapIndexed { index, annotation ->
            val range = parseAnnotationRange(spanned, annotation)
            if (range.first == -1 || range.last == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            StringAnnotation(annotation, range.first, range.last, index)
        }

    /**
     * Retrieves [annotation]'s start and end positions in terms of specified [spanned].
     * If [annotation] is `null`, then we assume that is a top-most root, and return full [spanned]
     * range.
     */
    fun parseAnnotationRange(
        spanned: Spanned,
        annotation: Annotation?
    ): IntRange {
        annotation ?: return 0..spanned.length
        val start = spanned.getSpanStart(annotation)
        val end = spanned.getSpanEnd(annotation)
        return start..end
    }

    /**
     * Variant of [parseAnnotationRange] that works with [annotations] array.
     */
    fun parseAnnotationRanges(
        spanned: Spanned,
        annotations: Array<out Annotation>
    ): List<IntRange> =
        annotations.map { parseAnnotationRange(spanned, it) }

    fun parseAnnotations(
        context: Context,
        annotations: Array<out Annotation>,
        resolver: AnnotationProcessorResolver,
        args: ValueArgs
    ): List<CharacterStyle?> =
        annotations.map { annotation ->
            parseAnnotation(context, annotation, resolver, args)
        }

    fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        resolver: AnnotationProcessorResolver,
        args: ValueArgs
    ): CharacterStyle? =
        resolver.resolve(annotation.key)?.parseAnnotation(context, annotation, args)
}