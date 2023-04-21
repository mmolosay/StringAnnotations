package io.github.mmolosays.stringannotations.args.types

/**
 * Represents some trivial graphic-wise text decoration, e.g. line.
 *
 * Default instances are [Underline] and [Striketrhough].
 */
public class TextDecoration private constructor(public val id: Int) {

    public companion object {

        /**
         * Line below the text.
         */
        public val Underline: TextDecoration =
            TextDecoration(0)

        /**
         * Line over the text.
         */
        public val Striketrhough: TextDecoration =
            TextDecoration(1)

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