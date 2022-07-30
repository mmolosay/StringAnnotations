package com.example.stringannotations.processors

import android.text.Spanned
import com.example.stringannotations.tree.AnnotationNode
import com.example.stringannotations.tree.hasChildren

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
        val nonAnnotationranges = ranges.filterIndexed { index, range ->
            index % 2 == 0
        }
        return reduceZeroLengthRanges(nonAnnotationranges)
    }

    /**
     * Calculates inner ranges of depth=1 of [node] in specified [string].
     *
     * Sample:
     * ```
     * Node: ____-‾-____--‾‾_
     * Ranges: [{0, 4}, {4, 7}, {7, 11}, {11, 15}, {15, 16}]
     * ```
     *
     * Case 1:
     * "first is %1$s, %2$s and %3$s"
     *
     * first is %1$s, red  and %3$s
     */
    private fun findAllNodeRanges(
        node: AnnotationNode,
        string: Spanned
    ): List<IntRange> {
        val ranges = mutableListOf<IntRange>()
        val nodeRange = AnnotationProcessor.getAnnotationRange(string, node.annotation)
        if (!node.hasChildren()) return ranges.apply { add(nodeRange) } // leaf nodes contain one range
        val childrenRanges = node.children.map { child ->
            AnnotationProcessor.getAnnotationRange(string, child.annotation)
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