package io.github.mmolosays.stringannotations.tree

import android.text.Annotation
import android.text.Spanned
import io.github.mmolosays.stringannotations.AnnotationSpanProcessor
import io.github.mmolosays.stringannotations.PlacedAnnotation
import io.github.mmolosays.stringannotations.has

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

internal object AnnotationTreeBuilder {

    /**
     * Assembles annotation tree out of [Annotation]s of specified [string].
     */
    fun buildAnnotationTree(
        string: Spanned,
        annotations: Array<out Annotation>,
    ): AnnotationTree {
        val placed = AnnotationSpanProcessor.parseStringAnnotations(string, annotations)
        val groups = placed.groupInRoot()
        val topmosts = groups.map { group ->
            val root = group.first() // due to impl of groupInRoot()
            parseAnnotationNode(root, group)
        }
        return AnnotationTree(children = topmosts)
    }

    /**
     * Creates new [AnnotationNode] with specified [annotation].
     * [AnnotationNode.children] will be sought in [group].
     */
    private fun parseAnnotationNode(
        annotation: PlacedAnnotation,
        group: List<PlacedAnnotation>,
    ): AnnotationNode {
        val children = annotation.findDirectChildrenIn(group).map { child ->
            parseAnnotationNode(child, group)
        }
        return AnnotationNode(annotation.annotation, children)
    }

    /**
     * Finds direct children in [annotations] list for the receiver parent annotation.
     */
    private infix fun PlacedAnnotation.findDirectChildrenIn(
        annotations: List<PlacedAnnotation>,
    ): List<PlacedAnnotation> {
        val index = annotations.indexOf(this)
        require(index != -1) { "this parent is not in annotations list" }
        if (index == annotations.lastIndex) return emptyList() // last annotation can't have children
        val children = mutableListOf<PlacedAnnotation>()
        for (i in index + 1 until annotations.size) { // prior annotation can't be child
            val maybeChild = annotations[i]
            val parent = maybeChild findDirectParentIn annotations
            if (this === parent) {
                children.add(maybeChild)
            }
        }
        return children
    }

    /**
     * Finds direct parent in [annotations] list for the receiver child annotation.
     */
    private infix fun PlacedAnnotation.findDirectParentIn(
        annotations: List<PlacedAnnotation>,
    ): PlacedAnnotation? {
        val index = annotations.indexOf(this)
        if (index == 0) return null // first annotation is always root, thus no parent
        var i = index - 1
        while (i >= 0) {
            val maybeParent = annotations[i]
            if (maybeParent.has(this)) return maybeParent
            i--
        }
        return null // this annotation is a root
    }

    /**
     * Groups receiver annotations in a groups (lists), where first element is a root annotations,
     * and all other are its children (direct and inderect).
     *
     * Sample:
     * ```
     * Tree of annotations:
     *   to            <- level-2 children
     *  duo a   so be  <- level-1 children
     * numbers silver  <- root (level-0) annotations
     *
     * After split:
     * [
     *     [numbers, duo, to, a],
     *     [silver, so, be]
     * ]
     */
    private fun List<PlacedAnnotation>.groupInRoot(): List<List<PlacedAnnotation>> {
        if (size == 1) return listOf(this)
        val groups = mutableListOf<List<PlacedAnnotation>>()
        var lastRootIndex = 0
        val lastRoot = { get(lastRootIndex) }
        for (i in 1 until size) {
            val annotation = get(i)
            if (!lastRoot().has(annotation)) { // if annotation is a root itself
                groups += slice(lastRootIndex until i)
                lastRootIndex = i
            }
        }
        // last are unadded yet
        groups += slice(lastRootIndex..lastIndex)
        return groups
    }
}