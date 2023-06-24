package io.github.mmolosays.stringannotations.tree

import android.text.Annotation

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
 * Node of a tree-like structure of [AnnotationNode]s.
 */
internal sealed interface TreeNode {

    /**
     * Direct children of this node.
     */
    val children: List<AnnotationNode>
}

/**
 * A root of annotation tree.
 * Its [children] are top-most annotations in the string.
 */
internal data class AnnotationTree(
    override val children: List<AnnotationNode>,
) : TreeNode

/**
 * A node of annotation tree.
 * Defined by its [annotation]. May contain other ones indside its range.
 */
internal data class AnnotationNode(
    /**
     * The [Annotation] object instance itself.
     */
    val annotation: Annotation,

    /**
     * Direct children of [annotation].
     * Their [annotation]s are positioned inside a range of this [annotation].
     */
    override val children: List<AnnotationNode>,
) : TreeNode