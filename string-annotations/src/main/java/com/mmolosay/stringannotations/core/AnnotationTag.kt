package com.mmolosay.stringannotations.core

/**
 * Represents deconstructed string annotation tag, where [type] is its attribute
 * and [tokens] is its value, split into individual atomic tokens.
 */
public data class AnnotationTag internal constructor(
    val type: Type,
    val tokens: List<Token>
) {

    /**
     * Represents some string annotation tag's attribute.
     */
    @JvmInline
    public value class Type(public val string: String) {

        override fun toString(): String =
            string
    }

    /**
     * Represents some atomic value, that was parsed from string annotation's tag.
     */
    @JvmInline
    public value class Token(public val string: String) {

        override fun toString(): String =
            string
    }
}