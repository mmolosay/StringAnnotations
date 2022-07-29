package com.example.stringannotations.ant

import android.content.Context
import android.content.res.Resources
import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.core.text.getSpans

object AnnotatedStrings {

    fun format(
        context: Context,
        @StringRes stringRes: Int,
        vararg args: String
    ): Spanned =
        format(
            resources = context.resources,
            stringRes = stringRes,
            args = args
        )

    fun format(
        resources: Resources,
        @StringRes stringRes: Int,
        vararg args: String
    ): Spanned =
        format(
            string = resources.getText(stringRes) as SpannedString,
            args = args
        )

    /**
     *
     * Case 1:
     * Input: "first: %s, <annotation>second: %s</annotation>, third: <annotation>%s</annotation>"
     * Output: "first: red, second: green, third: blue"
     * Arguments: ["red", "green", "blue"]
     * Ranges In: [{11, 21}, {30, 32}]
     * Ranges Out: [{11, 21}, {30, 32}]
     *
     * Case 2:
     * Input: "abc <defgh <<i> <jk>>> lmno <pqr> st"
     * Ranges: [{4, 14}, {10, 14}, {10, 11}, {12, 14}, {20, 23}]
     */
    private fun format(
        string: SpannedString,
        vararg args: String
    ): Spanned {
        val annotations = getStringAnnotations(string)
        val outer = filterOuterAnnotations(string, annotations)
        val builder = SpannableStringBuilder(string).apply {
            val a = parseCompoundAnnotations(string, annotations)
            println() // TODO: remove
        }
        println() // TODO: remove
//        val formatted = SpannableStringBuilder(string).apply {
//            outer.forEach { annotation ->
//                val start = getSpanStart(annotation)
//                val end = getSpanEnd(annotation)
//                val formatted = String.format(anno)
//            }
//        }
//        annotations.forEachIndexed { i, annotation ->
//            val strargs = args.getOrNull(i)?.args ?: return@forEachIndexed
//            val start = builder.getSpanStart(annotation)
//            val end = builder.getSpanEnd(annotation)
//            val annotated = substring(start, end)
//            val formatted = String.format(annotated, strargs)
//            replace(start, end, formatted)
//        }
//        return builder
        return builder
    }

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     */
    private fun getStringAnnotations(string: SpannedString): Array<out Annotation> =
        string.getSpans()

    /*
    private fun filterOuterAnnotations(string: SpannedString): Array<out Annotation> {
        val annotations = getStringAnnotations(string)
        return filterOuterAnnotations(string, annotations)
    }
    */

    /*
    /**
     * Outer annotation is the one that doesn't belong to some another one (lies at string's top level).
     */
    private fun filterOuterAnnotations(
        string: SpannedString,
        annotations: Array<out Annotation>
    ): Array<out Annotation> {
        val list = mutableListOf<Annotation>()
        val ranges = annotations.map { annotation ->
            val start = string.getSpanStart(annotation)
            val end = string.getSpanEnd(annotation)
            if (start == -1 || end == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            return@map start..end
        }
        for (i in ranges.indices) {
            val range = ranges[i]
            val hasOuterRange = ranges.any { other ->
                val a = (range !== other)            // is not the same exact object
                val b = (other.first <= range.first) // other is not starting later
                val c = (other.last >= range.last)   // other is not ending before
                a && b && c
            }
            if (!hasOuterRange) list += annotations[i]
        }
        return list.toTypedArray()
    }
    */

    private fun parseCompoundAnnotations(
        string: SpannedString,
        annotations: Array<out Annotation>
    ): List<CompoundAnnotation> {
        val list = mutableListOf<CompoundAnnotation>()
        if (annotations.isEmpty()) return list
        val ranges = parseRangedAnnotations(string, annotations)
        val outerIndices = filterOuterAnnotationsIndices(ranges)
        outerIndices.forEach { index ->
            list += parseCompoundAnnotationRecursively(ranges, index)
        }
        return list
    }

    /**
     * Recursive function, that
     */
    private fun parseCompoundAnnotationRecursively(
        ranges: List<RangedAnnotation>,
        targetIndex: Int = 0 // first annotation is always outer
    ): CompoundAnnotation {
        val ranged = ranges[targetIndex]
        val children = getDirectChildrenIndices(ranges, targetIndex)
        if (children.isNotEmpty()) {
            CompoundAnnotation(ranged.annotation, children.map { ranges[it] })
        } else {
            return CompoundAnnotation(ranged.annotation, emptyList())
        }
    }

    /**
     * Filter direct children from [ranges] of annotation from [ranges] at specified [parentIndex].
     *
     * This function will work properly only if annotations are in sequential order respectfully of
     * their declaration order.
     */
    private fun getDirectChildrenIndices(
        ranges: List<RangedAnnotation>,
        parentIndex: Int
    ): List<Int> {
        if (parentIndex == ranges.lastIndex) return emptyList() // last annotation never has children
        val ranged = ranges.getOrNull(parentIndex) ?: return emptyList()
        val indices = mutableListOf<Int>()
        for (i in parentIndex + 1 until indices.size) {
            val maybeChild = ranges[i]
            if (ranged.has(maybeChild)) indices += i
            else return indices
        }
        return indices
    }


    private fun filterDirectChildren(
        ranges: List<RangedAnnotation>,
        parentIndex: Int
    ): List<RangedAnnotation> =
        getDirectChildrenIndices(ranges, parentIndex).map { i -> ranges[i] }

    /**
     * Parses specified [annotations] of [string] into list of [RangedAnnotation].
     */
    private fun parseRangedAnnotations(
        string: SpannedString,
        annotations: Array<out Annotation>
    ): List<RangedAnnotation> =
        annotations.map { annotation ->
            val start = string.getSpanStart(annotation)
            val end = string.getSpanEnd(annotation)
            if (start == -1 || end == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            RangedAnnotation(annotation, start, end)
        }

    /**
     * Filters "outer" annotations'.
     *
     * "Outer" annotation is the one that is not child of some other one — it lies at the most
     * top level of string.
     */
    private fun filterOuterAnnotationsIndices(
        ranges: List<RangedAnnotation>
    ): List<Int> {
        val indices = mutableListOf<Int>()
        if (ranges.isEmpty()) return indices
        var lastIndex = 0 // first annotation is always outer
        var lastOuter = ranges[lastIndex]
        indices += lastIndex
        for (i in 1 until ranges.size) {
            val annotation = ranges[i]
            if (!lastOuter.has(annotation)) {
                lastIndex = i
                lastOuter = annotation
                indices += lastIndex
            }
        }
        return indices
    }

    private fun filterOuterAnnotations(
        ranges: List<RangedAnnotation>
    ): List<RangedAnnotation> =
        filterOuterAnnotationsIndices(ranges).map { i -> ranges[i] }

    /**
     * [Annotation] that may contain other ones.
     */
    data class CompoundAnnotation(
        val self: Annotation,
        val children: List<Annotation>
    )

    /**
     * [Annotation] which has [start] and [end] positions in terms of some string.
     */
    data class RangedAnnotation(
        val annotation: Annotation,
        val start: Int,
        val end: Int
    )

    /**
     * Determines if [other] annotation is "inside" `this` one.
     * "Inside" means that it may be direct or inderect (nested) child.
     *
     * @return `true`, if [other] is inside, or `false`, if it's not or ([other] === `this`).
     */
    fun RangedAnnotation.has(other: RangedAnnotation): Boolean {
        val a = (this !== other)            // is not the same exact object
        val b = (other.start >= this.start) // other is not starting earlier
        val c = (other.end <= this.end)     // other is not ending later
        return (a && b && c)
    }
}