package io.github.mmolosays.stringannotations.args.types

/**
 * Represents some trivial graphic-wise text decoration, e.g. line.
 *
 * Default instances are [Underline] and [Striketrhough].
 */
public class TextDecoration private constructor(public val id: Int) {

    override fun toString(): String =
        when (this) {
            Underline -> "Underline"
            Striketrhough -> "Striketrhough"
            else -> "TextDecoration($id)"
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextDecoration) return false
        return (this.id == other.id)
    }

    override fun hashCode(): Int {
        return id
    }

    public companion object {

        /**
         * Line below the text.
         */
        public val Underline: TextDecoration by lazy {
            TextDecoration(0)
        }

        /**
         * Line over the text.
         */
        public val Striketrhough: TextDecoration by lazy {
            TextDecoration(1)
        }

        /**
         * Creates [TextDecoration], based on specified [id].
         *
         * @return custom [TextDecoration], or one of predefined ones, if [id] matches.
         */
        public fun Custom(id: Int): TextDecoration =
            when (id) {
                Underline.id -> Underline
                Striketrhough.id -> Striketrhough
                else -> TextDecoration(id)
            }
    }
}