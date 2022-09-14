package com.mmolosay.stringannotations.internal

import android.text.Spanned

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
 * Processes [AnnotationNode]s.
 */
internal object AnnotationNodeProcessor {

    /**
     * Calculates inner ranges of [node] in specified [string], which are not ocupied by children
     * nodes.
     *
     * Sample:
     * ```
     * Node: ____-‾-____--‾‾_
     * Ranges: [{0, 4}, {7, 11}, {15, 16}]
     * ```
     */
    fun findNodeNonAnnotationRanges(
        node: AnnotationNode,
        string: Spanned
    ): List<IntRange> {
        val ranges = findAllNodeRanges(node, string)
        val nonAnnotationRanges = ranges.filterIndexed { index, _ ->
            index % 2 == 0 // each second range is an annotation (due to findAllNodeRanges impl)
        }
        return reduceZeroLengthRanges(nonAnnotationRanges)
    }

    /**
     * Calculates inner ranges of depth=1 of [node] in specified [string].
     *
     * Sample:
     * ```
     * Node: ____-‾-____--‾‾_
     * Ranges: [{0, 4}, {4, 7}, {7, 11}, {11, 15}, {15, 16}]
     * ```
     */
    private fun findAllNodeRanges(
        node: AnnotationNode,
        string: Spanned
    ): List<IntRange> {
        val ranges = mutableListOf<IntRange>()
        val nodeRange = AnnotationSpanProcessor.parseAnnotationRange(string, node.annotation)
        if (!node.hasChildren()) return ranges.apply { add(nodeRange) } // leaf nodes contain one range
        val childrenRanges = node.children.map { child ->
            AnnotationSpanProcessor.parseAnnotationRange(string, child.annotation)
        }
        val childrenCount = node.children.size
        val spaceCount = childrenCount + 1 // __-___--_ 3 spaces for 2 children, 5 total ranges
        val rangeCount = childrenCount + spaceCount

        var lastEnd = nodeRange.first
        var start: Int
        var end: Int
        for (i in 0 until rangeCount) {
            start = lastEnd
            val childrange =
                childrenRanges.getOrNull(i / 2) ?: break // current or nearest right child
            end = if (i % 2 == 0) { // each even index stands for space range
                childrange.first
            } else {
                childrange.last
            }
            lastEnd = end
            ranges.add(start..end)
        }
        return ranges
    }

    /**
     * Filters out ranges of zero length (`start == end`).
     */
    private fun reduceZeroLengthRanges(
        ranges: List<IntRange>
    ): List<IntRange> =
        ranges.filter { range ->
            range.last - range.first != 0
        }
}