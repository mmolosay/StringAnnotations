package com.mmolosay.stringannotations.core

/**
 * Represents deconstructed string annotation tag, where [type] is its attribute
 * and [values] is its value, split into individual atomic tokens.
 */
public data class AnnotationTag internal constructor(
    val type: Type,
    val values: List<Value>
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
     * Represents some string annotation tag's value.
     */
    @JvmInline
    public value class Value(public val string: String) {

        override fun toString(): String =
            string
    }
}