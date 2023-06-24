package io.github.mmolosays.stringannotations.tree

import android.text.Spanned
import io.github.mmolosays.stringannotations.AnnotationSpanProcessor.rangeOf

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
 * Processes [AnnotationNode]s.
 */
internal object AnnotationNodeProcessor {

    /**
     * Calculates inner ranges of [node] in specified [string], which are not ocupied by child
     * nodes.
     *
     * Sample:
     * ```
     * Node.children: _  ___——    ___
     *          Node: ________________
     * Result ranges: [{1, 3}, {8, 12}, {15, 16}]
     * ```
     */
    fun findNodeUnoccupiedRanges(
        node: TreeNode,
        string: Spanned,
    ): List<IntRange> {
        val nodeRange = when (node) {
            is AnnotationNode -> string rangeOf node.annotation
            is AnnotationTree -> 0..string.length
        }
        if (node.children.isEmpty()) return listOf(nodeRange) // leaf nodes contain one range – itself

        val ranges = mutableListOf<IntRange>()
        val childrenRanges = node.children.map { child ->
            string rangeOf requireNotNull(child.annotation)
        }

        for (i in childrenRanges.indices) {
            val childRange = childrenRanges[i]
            val start = childrenRanges.getOrNull(i - 1)?.last ?: 0 // end of previous child range
            val end = childRange.first
            if (start - end != 0) { // ignore zero-length ranges
                ranges += (start..end)
            }
        }
        // if last child ended prior to the end of the node itself, add remaining range
        if (childrenRanges.last().last != nodeRange.last) {
            ranges += (childrenRanges.last().last..nodeRange.last)
        }
        return ranges
    }
}