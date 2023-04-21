package io.github.mmolosays.stringannotations.processor.confaltor

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
 * Implementation of [ValuesReducer] with separated picking and reducing logic.
 *
 * Utilizes [pickingStrategy] to pick desired values and
 * [reducingStrategy] to conflate them into final result.
 */
public class StrategyReducer<V>(
    private val pickingStrategy: PickingStrategy<V>,
    private val reducingStrategy: ReducingStrategy<V>,
) : ValuesReducer<V> {

    /**
     * Picks desired values, using [pickingStrategy] and then reduces them
     * to one final result, using [reducingStrategy].
     */
    override fun reduce(values: List<V>): V? =
        values
            .let { pickingStrategy.on(it) }
            .let { reducingStrategy.on(it) }

    public companion object {

        /**
         * Picks and uses very first value.
         */
        public fun <V> First(): StrategyReducer<V> =
            StrategyReducer(
                pickingStrategy = PickingStrategy.First(),
                reducingStrategy = ReducingStrategy.Single(),
            )

        /**
         * Picks all values and reduces them with specified [reducer].
         */
        public fun <V> All(reducer: (values: List<V>) -> V?): StrategyReducer<V> =
            StrategyReducer(
                pickingStrategy = PickingStrategy.All(),
                reducingStrategy = ReducingStrategy.Multiple(reducer),
            )
    }

    /**
     * Specifies a way of selecting specific values.
     */
    public fun interface PickingStrategy<V> {

        /**
         * Applies this strategy on [values], returning collection of selected ones.
         */
        public fun on(values: List<V>): List<V>

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
        }
    }

    /**
     * Specifies a way of reducing values of type [V] into single result of the same type.
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
}