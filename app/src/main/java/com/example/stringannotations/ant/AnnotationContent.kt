package com.example.stringannotations.ant

/**
 * String argument of annotation.
 */
internal typealias Argument = String

/**
 * Contents of annotation.
 */
sealed interface AnnotationContent {

    val args: Array<out Argument>

    /**
     * Contents of annotations, that do not have nested annotations inside.
     * Basicly, only string arguments.
     */
    class Basic(vararg args: Argument) : AnnotationContent {

        override val args: Array<out Argument> = args
    }

    /**
     * Contents of annotations, that do have nested annotations inside.
     *
     * @param items either [Argument] arg or [Compound] or [Basic].
     *
     * <annotation>
     *     %s and <annotation>%s</annotation> and %s
     * </annotation>
     */
    class Compound(vararg items: Any) : AnnotationContent {

        override val args: Array<out Argument> =
            items.filterIsInstance<Argument>().toTypedArray()
    }
}