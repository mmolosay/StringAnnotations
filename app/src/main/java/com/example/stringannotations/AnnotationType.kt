package com.example.stringannotations

/**
 * Annotation of some specific (or combined) type.
 * See derived classes.
 */
sealed interface AnnotationType {

    /**
     * Annotation, which specifies color of its body.
     *
     * ```
     * <annotation color="#ff0000">red text</annotation>
     * ```
     */
    class ColorHex(val color: Int) : AnnotationType

    /**
     * Annotation, which specifies color of its body.
     *
     * ```
     * <annotation color-res="yourColorResName">colored text</annotation>
     * ```
     */
    class ColorRes(val color: Int) : AnnotationType

    class Background(val color: Int) : AnnotationType

    class Combined(val types: List<AnnotationType>) : AnnotationType

    object Unknown : AnnotationType
}