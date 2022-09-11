package com.mmolosay.stringannotations.core

/**
 * Represents some string annotation tag's value.
 */
@JvmInline
public value class AnnotationValue(public val string: String) {

    override fun toString(): String =
        string
}