package com.example.stringannotations

/**
 * Annotation of some specific single type.
 *
 * All `<annotation>`s with more then one attribute will be split into multiple,
 * so that each has only one.
 */
sealed interface AnnotationType {

    /**
     * Annotation, which specified background color of its body.
     *
     * ```
     * HEX color:
     * <annotation background="#ff0000">text with red background</annotation>
     *
     * Generic color name:
     * <annotation background="green">text with green background</annotation>
     *
     * Color resource name:
     * <annotation background="yourColorResName">text with colored background</annotation>
     * ```
     */
    class Background(val color: Int) : AnnotationType

    /**
     * Annotation, which specifies foreground color of its body.
     *
     * ```
     * HEX color:
     * <annotation color="#ff0000">red text</annotation>
     *
     * Generic color name:
     * <annotation color="green">green text</annotation>
     *
     * Color resource name:
     * <annotation color="yourColorResName">colored text</annotation>
     * ```
     */
    class Foreground(val color: Int) : AnnotationType

    class TypefaceStyle(val style: Int) : AnnotationType

    object UnderlineStyle : AnnotationType

    object Unknown : AnnotationType
}