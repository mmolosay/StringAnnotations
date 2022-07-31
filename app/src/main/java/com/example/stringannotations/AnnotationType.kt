package com.example.stringannotations

import android.text.style.ClickableSpan

/**
 * Annotation of some single specific type.
 *
 * One should inherit this interface in order to define custom type.
 */
interface AnnotationType {

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

    class Clickable(val span: ClickableSpan) : AnnotationType

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

    /**
     * Annotation, which has unknown attribute, or its value(s) can not be processed properly.
     * It will not be ignored and converted in any span.
     */
    object Null : AnnotationType
}