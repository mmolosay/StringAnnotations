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
    ): AnnotationNode {
        val roots = buildAnnotationTreeRoots(string, annotations)
        return AnnotationNode(annotation = null, children = roots)
    }

    /**
     * Assembles roots of annotation tree of specified [string] and its [annotations].
     */
    private fun buildAnnotationTreeRoots(
        string: Spanned,
        annotations: Array<out Annotation>,
    ): List<AnnotationNode> {
        val parsed = AnnotationSpanProcessor.parseStringAnnotations(string, annotations)
        val roots = findRootAnnotations(parsed)
        val groups = groupByRoots(roots, parsed)
        return groups.map { rootGroup ->
            buildAnnotationTreeRoot(rootGroup)
        }
    }

    /**
     * Assembles root of annotation tree of specified [rootGroup].
     *
     * @see groupByRoots
     */
    private fun buildAnnotationTreeRoot(
        rootGroup: List<PlacedAnnotation>,
    ): AnnotationNode {
        val root = rootGroup.first()
        return parseAnnotationNode(root, rootGroup)
    }

    private fun parseAnnotationNode(
        annotation: PlacedAnnotation,
        group: List<PlacedAnnotation>,
    ): AnnotationNode {
        val children = findDirectChildren(annotation, group).map { child ->
            parseAnnotationNode(child, group)
        }
        return AnnotationNode(annotation.annotation, children)
    }

    /**
     * Finds root annotations and returns their indices in [annotations] list.
     *
     * Traveres throug [annotations], remembering last found root.
     * First annotation is always a root, since it has no parent (it's a very first).
     * Current annotation is being checked, whether last root has it as direct or indirect child.
     * If it is not, then current annotation is a next root and being added into return list.
     * Repeat.
     */
    private fun findRootAnnotations(
        annotations: List<PlacedAnnotation>,
    ): List<PlacedAnnotation> =
        mutableListOf<PlacedAnnotation>().apply {
            if (annotations.isEmpty()) return this
            var lastRoot: PlacedAnnotation? = null // first annotation is always a root
            for (annotation in annotations) {
                if (lastRoot?.has(annotation) != true) {
                    lastRoot = annotation
                    add(annotation)
                }
            }
        }

    private fun findDirectChildren(
        parent: PlacedAnnotation,
        group: List<PlacedAnnotation>,
    ): List<PlacedAnnotation> {
        require(group.contains(parent)) { "this parent is not in annotations list" }
        val children = mutableListOf<PlacedAnnotation>()
        if (parent.index == group.lastIndex) return children // last annotation never has children
        for (i in parent.index + 1 until group.size) {
            val maybeChild = group[i]
            if (isAnnotationDirectChild(maybeChild, parent, group)) {
                children.add(maybeChild)
            }
        }
        return children
    }

    private fun isAnnotationDirectChild(
        annotation: PlacedAnnotation,
        maybeParent: PlacedAnnotation,
        annotations: List<PlacedAnnotation>,
    ): Boolean {
        val parent = findAnnotationDirectParent(annotation, annotations)
        return (maybeParent === parent)
    }

    private fun findAnnotationDirectParent(
        annotation: PlacedAnnotation,
        annotations: List<PlacedAnnotation>,
    ): PlacedAnnotation? {
        val index = annotation.index
        if (index == 0) return null // first annotation is always root, thus no parent
        var i = index - 1
        while (i >= 0) {
            val maybeParent = annotations[i]
            if (maybeParent.has(annotation)) return maybeParent
            i--
        }
        return null // this annotation is a root
    }

    /**
     * Splits [annotations] into sublists (groups), in which first element is a root annotation,
     * and all other are its direct and indirect children.
     *
     * Sample:
     * ```
     * Tree of annotations:
     *   __            <- level-2 children
     *  ___ _   __ __  <- level-1 children
     * _______ ______  <- root (level-0) annotations
     *
     * After split:
     * [
     *     [_______, ___, __, _],
     *     [______, __, __]
     * ]
     * ```
     */
    private fun groupByRoots(
        roots: List<PlacedAnnotation>,
        annotations: List<PlacedAnnotation>,
    ): List<List<PlacedAnnotation>> {
        if (roots.size == 1) return listOf(annotations)
        val groups = mutableListOf<List<PlacedAnnotation>>()
        for (i in roots.indices) {
            val from = roots[i].index
            val to = roots.getOrNull(i + 1)?.index ?: annotations.size
            val group = annotations.subList(from, to)
            groups.add(group)
        }
        return groups
    }
}