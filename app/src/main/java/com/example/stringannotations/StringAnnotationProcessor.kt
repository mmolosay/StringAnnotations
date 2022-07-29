package com.example.stringannotations

import android.text.Annotation
import android.text.SpannedString
import androidx.core.text.getSpans
import com.example.stringannotations.tree.AnnotationNode
import com.example.stringannotations.tree.AnnotationTree

// TODO: make a class and istantiate as a singleton?
internal object StringAnnotationProcessor {

    /**
     * Assembles [AnnotationTree] out of [Annotation]s of specified [string].
     */
    fun buildAnnotationTree(
        string: SpannedString
    ): AnnotationTree {
        val annotations = getStringAnnotations(string)
        val roots = buildAnnotationTreeRoots(string, annotations)
        return AnnotationTree(roots)
    }

    /**
     * Assembles roots of [AnnotationTree] of specified [string] and its [annotations].
     */
    private fun buildAnnotationTreeRoots(
        string: SpannedString,
        annotations: Array<out Annotation>
    ): List<AnnotationNode> {
        val placed = parsePlacedAnnotations(string, annotations)
        val roots = findRootAnnotations(placed)
        val groups = groupByRoots(roots, placed)
        return groups.map { rootGroup ->
            buildAnnotationTreeRoot(rootGroup)
        }
    }

    /**
     * Assembles root of [AnnotationTree] of specified [rootGroup].
     *
     * @see groupByRoots
     */
    private fun buildAnnotationTreeRoot(
        rootGroup: List<PlacedAnnotation>
    ): AnnotationNode {
        val root = rootGroup.first()
        return parseAnnotationNode(root, rootGroup)
    }

    private fun parseAnnotationNode(
        annotation: PlacedAnnotation,
        group: List<PlacedAnnotation>
    ): AnnotationNode {
        val children = findDirectChildren(annotation, group).map { child ->
            parseAnnotationNode(child, group)
        }
        return AnnotationNode(annotation.annotation, children)
    }

    /**
     * Parses specified [annotations] of [string] into list of [PlacedAnnotation].
     */
    private fun parsePlacedAnnotations(
        string: SpannedString,
        annotations: Array<out Annotation>
    ): List<PlacedAnnotation> =
        annotations.mapIndexed { index, annotation ->
            val start = string.getSpanStart(annotation)
            val end = string.getSpanEnd(annotation)
            if (start == -1 || end == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            PlacedAnnotation(annotation, start, end, index)
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
        annotations: List<PlacedAnnotation>
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
        group: List<PlacedAnnotation>
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
        annotations: List<PlacedAnnotation>
    ): Boolean {
        val parent = findAnnotationDirectParent(annotation, annotations)
        return (maybeParent === parent)
    }

    private fun findAnnotationDirectParent(
        annotation: PlacedAnnotation,
        annotations: List<PlacedAnnotation>
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
     *   __            <- second children
     *  ___ _   __ __  <- first children
     * _______ ______  <- root annotations
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
        annotations: List<PlacedAnnotation>
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

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     */
    private fun getStringAnnotations(string: SpannedString): Array<out Annotation> =
        string.getSpans()
}