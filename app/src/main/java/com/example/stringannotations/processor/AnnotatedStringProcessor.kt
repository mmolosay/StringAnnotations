package com.example.stringannotations.processor

import android.text.SpannableStringBuilder
import com.example.stringannotations.tree.AnnotationNode

internal object AnnotatedStringProcessor {

    fun format(
        builder: SpannableStringBuilder,
        node: AnnotationNode,
        vararg args: String
    ) {
        node.children.forEach { child ->
            format(builder, child, *args)
        }
        val ranges = AnnotationNodeProcessor.findNodeNonAnnotationRanges(node, builder)
        ranges.forEach { range ->
            val substring = builder.substring(range.first, range.last)
            val formatted = String.format(substring, *args)
            if (substring != formatted) { // do nothing is no formatting happened
                builder.replace(range.first, range.last, formatted)
            }
        }
    }
}