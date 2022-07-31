package com.example.stringannotations.processor

import android.text.SpannableStringBuilder
import com.example.stringannotations.tree.AnnotationNode

internal object AnnotatedStringProcessor {

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