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
 * Specifies a way of conflating values of type [V] into result of the same type.
 */
public fun interface ReducingStrategy<V> {

    /**
     * Applies this strategy on [values], returning result of reducing.
     */
    public fun on(values: List<V>): V?

    public companion object {

        /**
         * Picks first value and returns it.
         */
        public fun <V> Single(): ReducingStrategy<V> =
            ReducingStrategy { values ->
                values.firstOrNull()
            }

        /**
         * Picks all values and reduces them with specified [reducer].
         */
        public fun <V> Multiple(reducer: (values: List<V>) -> V?): ReducingStrategy<V> =
            ReducingStrategy(reducer)
    }
}