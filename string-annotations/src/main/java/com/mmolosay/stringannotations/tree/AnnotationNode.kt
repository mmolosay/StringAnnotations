package com.mmolosay.stringannotations.tree

import android.text.Annotation

/**
 * [Annotation] that may contain other ones.
 * Represents a node in tree-like structure.
 */
internal data class AnnotationNode(
    /**
     * The [Annotation] object instance itself.
     */
    val annotation: Annotation?,

    /**
     * Direct children of [annotation].
     */
    val children: List<AnnotationNode>
)

internal fun AnnotationNode.hasChildren(): Boolean =
    this.children.isNotEmpty()
