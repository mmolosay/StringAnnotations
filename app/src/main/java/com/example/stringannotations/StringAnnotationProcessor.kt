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
        val roots = mutableListOf<AnnotationNode>()
        if (annotations.isEmpty()) return roots
        val placeds = parsePlacedAnnotations(string, annotations)

    }

    /**
     *
     */
    private fun buildAnnotationTreeRoot(
        placeds: List<PlacedAnnotation>
    ): AnnotationNode {

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
     * Finds root annotations and returns their indices in [placeds] list.
     */
    private fun findRootAnnotationsIndices(
        placeds: List<PlacedAnnotation>
    ): List<Int> =
        mutableListOf<Int>().apply {
            if (placeds.isEmpty()) return this
            var lastIndex: Int? // first annotation is always a root
            var lastRoot: PlacedAnnotation? = null
            for (i in 1 until placeds.size) {
                val annotation = placeds[i]
                if (lastRoot?.has(annotation) != true) {
                    lastIndex = i
                    lastRoot = annotation
                    add(lastIndex)
                }
            }
        }

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     */
    private fun getStringAnnotations(string: SpannedString): Array<out Annotation> =
        string.getSpans()
}