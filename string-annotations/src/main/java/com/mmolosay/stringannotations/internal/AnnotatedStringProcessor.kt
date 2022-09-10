package com.mmolosay.stringannotations.internal

import android.text.SpannableStringBuilder

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
 * Processes annotated strings.
 */
internal object AnnotatedStringProcessor {

    /**
     * Formats specified [builder] string with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans. Specified [node] is a top-most root of [builder]'s
     * annotation tree.
     *
     * Formatting happens recursively, according to following scenario:
     *
     * 1. Recursively traverse to most deep leaf (childless) nodes, and format their bodies
     *    (since they don't have children, there is nothing to split in them).
     * 2. Return from leaf nodes to one level up to their parent and format their body parts,
     *    unoccupied by children (since they was already formatted in previous step).
     * 3. Repeat step 2. until the tree root (without annotation) is reached, then take its next
     *    child and repeat from 1.
     *
     * ```
     * Example of possible scenario:
     *
     * Node: ____-‾-____--‾‾_
     *
     * Steps (first number is an index of root in tree node, second is an iteration step ordinal):
     * 0.1: format node's {5, 6} whole body.
     * 0.2: format parent's unformatted body: {4, 5} and {6, 7}.
     * 1.1: format node's {13, 15} whole body.
     * 1.2: format parent's unformatted body: {11, 13}.
     * last: all roots are formatted, format left unannotated body parts: {0, 4}, {7, 11} and {15, 16}.
     * ```
     */
    fun format(
        builder: SpannableStringBuilder,
        node: AnnotationNode,
        formatArgs: Array<out String>
    ) {
        node.children.forEach { child ->
            format(builder, child, formatArgs)
        }
        val ranges = AnnotationNodeProcessor.findNodeNonAnnotationRanges(node, builder)
        ranges.forEach { range ->
            val substring = builder.substring(range.first, range.last)
            val formatted = String.format(substring, *formatArgs)
            if (substring != formatted) { // do nothing if no formatting happened
                builder.replace(range.first, range.last, formatted)
            }
        }
    }
}