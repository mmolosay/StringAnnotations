package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import io.github.mmolosays.stringannotations.tree.AnnotationNode
import io.github.mmolosays.stringannotations.tree.AnnotationNodeProcessor
import io.github.mmolosays.stringannotations.tree.AnnotationTreeBuilder

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
 * Formats annotated strings.
 */
internal object AnnotatedStringFormatter {

    /**
     * Formats specified [string] with [formatArgs], preserving `<annotation>` spans.
     */
    fun format(
        string: Spanned,
        annotations: Array<out Annotation>,
        vararg formatArgs: Any,
    ): Spannable {
        // 0. prepare dependencies
        val builder = SpannableStringBuilder(string) // copies spans
        val stringArgs = formatArgs.stringify()

        // 1. build annotation tree
        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        // 2. replace wildcards, preserving annotation spans
        format(builder, tree, stringArgs)

        return builder
    }

    /**
     * Formats specified [builder] string with [formatArgs] (see [String.format]),
     * preserving `<annotation>` spans of [node] and its children.
     *
     * Formatting happens recursively, according to following scenario:
     *
     * 1. Recursively traverse to most deep leaf (childless) nodes, and format their bodies
     *    (since they don't have children, there is nothing to split in them).
     * 2. Return from leaf nodes one level up to their parent and format their body parts,
     *    unoccupied by children (since they was already formatted in previous step).
     * 3. Repeat step 2 until the recursive call stack is clear, then take next
     *    child and repeat from 1.
     *
     * ```
     * Example of possible scenario:
     *
     *   Children #2:      _       __
     *   Children #1:     ___    ____
     *          Node: ________________
     *
     * Node combined: ____-‾-____--‾‾_
     *
     * Steps (first number is an index of root in tree node, second is an iteration step ordinal):
     * 0.1: format node's {5, 6} whole body.
     * 0.2: format parent's unformatted body: {4, 5} and {6, 7}.
     * 1.1: format node's {13, 15} whole body.
     * 1.2: format parent's unformatted body: {11, 13}.
     * last: all roots are formatted, format left unannotated body parts: {0, 4}, {7, 11} and {15, 16}.
     * ```
     */
    private fun format(
        builder: SpannableStringBuilder,
        node: AnnotationNode,
        formatArgs: Array<out String>,
    ) {
        node.children.forEach { child ->
            format(builder, child, formatArgs)
        }
        AnnotationNodeProcessor
            .findNodeUnoccupiedRanges(node, builder)
            .forEach { range ->
                val substring = builder.substring(range.first, range.last)
                val formatted = String.format(substring, *formatArgs)
                if (substring != formatted) { // do nothing if no formatting happened
                    builder.replace(range.first, range.last, formatted)
                }
            }
    }

    /**
     * Maps receiver `formatArgs` into array of [String]s by resolving their string values
     * (see [java.lang.String.valueOf]).
     */
    private fun Array<out Any>.stringify(): Array<out String> =
        Array<String>(size) { i ->
            java.lang.String.valueOf(get(i))
        }
}