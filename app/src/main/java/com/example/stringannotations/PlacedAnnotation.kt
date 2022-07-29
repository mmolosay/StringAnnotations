package com.example.stringannotations

import android.text.Annotation

/**
 * [Annotation] which has [start] and [end] positions in terms of some string.
 * It allows to work with annotation placement data without context of specific string.
 */
internal data class PlacedAnnotation(
    val annotation: Annotation,
    val start: Int,
    val end: Int,
    val ordinal: Int
)

/**
 * Determines if [other] annotation is "inside" `this` one.
 * "Inside" means that it may be direct or inderect (nested) child.
 *
 * @return `true`, if [other] is inside, or `false`, if it's not or ([other] === `this`).
 */
internal fun PlacedAnnotation.has(other: PlacedAnnotation): Boolean {
    val a = (this !== other)            // is not the same exact object
    val b = (other.start >= this.start) // other is not starting earlier
    val c = (other.end <= this.end)     // other is not ending later
    return (a && b && c)
}