package com.example.stringannotations

/**
 * Annotation of some specific (or combined) type.
 * See derived classes.
 */
sealed interface AnnotationType {

    class Background(val color: Int) : AnnotationType

    /**
     * Annotation, which specifies color of its body.
     *
     * ```
     * HEX color:
     * <annotation color="#ff0000">red text</annotation>
     *
     * Color resource name:
     * <annotation color-res="yourColorResName">colored text</annotation>
     * ```
     */
    class Color(val color: Int) : AnnotationType

    class Combined(val types: List<AnnotationType>) : AnnotationType

    object Unknown : AnnotationType
}