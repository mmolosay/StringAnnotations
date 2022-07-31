package com.example.stringannotations.processor

import android.text.SpannableStringBuilder
import com.example.stringannotations.tree.AnnotationNode

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
        vararg formatArgs: String
    ) {
        node.children.forEach { child ->
            format(builder, child, *formatArgs)
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