package com.mmolosay.stringannotations.processor.token

/**
 * Represents some atomic value, that was parsed from string annotation's tag.
 */
@JvmInline
public value class Token(public val string: String) {

    override fun toString(): String =
        string
}