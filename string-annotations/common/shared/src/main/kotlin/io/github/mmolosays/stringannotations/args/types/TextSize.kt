package io.github.mmolosays.stringannotations.args.types

/*
 * Copyright 2023 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Size of text, defined in some units.
 */
public data class TextSize(
    val value: Float,
    val unit: SizeUnit,
) {

    public class SizeUnit private constructor(public val id: Int) {

        override fun toString(): String =
            when (this) {
                Px -> "Px"
                Sp -> "Sp"
                else -> "SizeUnit($id)"
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is SizeUnit) return false
            return (this.id == other.id)
        }

        override fun hashCode(): Int {
            return id
        }

        public companion object {

            /**
             * Corresponds to actial pixels on the screen with 1:1 ratio.
             */
            public val Px: SizeUnit by lazy {
                SizeUnit(0)
            }

            /**
             * Scalable Pixels.
             * Based on the density of the screen, and additionaly scaled by the user's font size preferences.
             *
             * This unit is recommended for the majority of [TextSize]s.
             */
            public val Sp: SizeUnit by lazy {
                SizeUnit(1)
            }

            /**
             * Creates [SizeUnit], based on specified [id].
             *
             * @return custom [SizeUnit], or one of predefined ones, if [id] matches.
             */
            public fun Custom(id: Int): SizeUnit =
                when (id) {
                    Px.id -> Px
                    Sp.id -> Sp
                    else -> SizeUnit(id)
                }
        }
    }
}