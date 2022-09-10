package com.mmolosay.stringannotations.values

/*
 * Copyright 2022 Mikhail Malasai
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
 * Specifies a way of selecting certain values.
 */
public fun interface ValuesPickingStrategy<V> {

    public fun pick(values: Sequence<V>): List<V>

    public companion object {

        /**
         * Picks only first value.
         */
        public fun <V> First(): ValuesPickingStrategy<V> =
            ValuesPickingStrategy { values ->
                values.firstOrNull()?.let { listOf(it) } ?: emptyList()
            }

        /**
         * Picks all values.
         */
        public fun <V> All(): ValuesPickingStrategy<V> =
            ValuesPickingStrategy { values ->
                values.toList()
            }

        /**
         * Pick values, matching specified [predicate].
         */
        public fun <V> Selectively(predicate: (value: V) -> Boolean): ValuesPickingStrategy<V> =
            ValuesPickingStrategy { values ->
                values.filter(predicate).toList()
            }
    }
}