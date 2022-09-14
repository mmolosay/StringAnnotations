package com.mmolosay.stringannotations.core

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
 * Transforms values of type [V] into final value of the same type.
 * It uses [pickingStrategy] to pick desired values and
 * [reducingStrategy] to conflate them into final result.
 */
public class Evaluator<V>(
    private val pickingStrategy: PickingStrategy<V>,
    private val reducingStrategy: ReducingStrategy<V>
) {

    /**
     * Picks desired values, using specified [pickingStrategy] and then reduces them
     * to one final result, using specified [reducingStrategy].
     */
    public fun evaluate(values: Sequence<V>): V? =
        values
            .let { pickingStrategy.on(it) }
            .let { reducingStrategy.on(it) }

    /**
     * Specifies a way of selecting specific values.
     */
    public fun interface PickingStrategy<V> {

        /**
         * Applies this strategy on [values], returning list of selected ones.
         */
        public fun on(values: Sequence<V>): List<V>

        public companion object {

            /**
             * Picks only first value.
             */
            public fun <V> First(): PickingStrategy<V> =
                PickingStrategy { values ->
                    values.firstOrNull()?.let { listOf(it) } ?: emptyList()
                }

            /**
             * Picks all values.
             */
            public fun <V> All(): PickingStrategy<V> =
                PickingStrategy { values ->
                    values.toList()
                }

            /**
             * Picks values, matching specified [predicate].
             */
            public fun <V> Selectively(predicate: (value: V) -> Boolean): PickingStrategy<V> =
                PickingStrategy { values ->
                    values.filter(predicate).toList()
                }
        }
    }

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

    public companion object {

        /**
         * Picks and uses very first value.
         */
        public fun <V> Single(): Evaluator<V> =
            Evaluator(
                pickingStrategy = PickingStrategy.First(),
                reducingStrategy = ReducingStrategy.Single()
            )

        /**
         * Picks all values and reduces them with specified [reducer].
         */
        public fun <V> All(reducer: (values: List<V>) -> V?): Evaluator<V> =
            Evaluator(
                pickingStrategy = PickingStrategy.All(),
                reducingStrategy = ReducingStrategy.Multiple(reducer)
            )
    }
}