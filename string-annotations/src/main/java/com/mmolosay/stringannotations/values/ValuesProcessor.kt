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
 * Transforms values of type [V] into final value of the same type.
 * It uses [pickingStrategy] strategy to pick desired values and
 * [reducingStrategy] to conflate them into final result.
 */
public class ValuesProcessor<V>(
    private val pickingStrategy: PickingStrategy<V>,
    private val reducingStrategy: ReducingStrategy<V>
) {

    /**
     * Picks desired values, using specified [pickingStrategy] and then reduces them
     * to one final result, using specified [reducingStrategy].
     */
    public fun process(values: Sequence<V>): V? =
        values
            .let { pickingStrategy.on(it) }
            .let { reducingStrategy.on(it) }

    public companion object {

        /**
         * Picks and uses very first value.
         */
        public fun <V> Single(): ValuesProcessor<V> =
            ValuesProcessor(
                pickingStrategy = PickingStrategy.First(),
                reducingStrategy = ReducingStrategy.Single()
            )

        /**
         * Picks all values and reduces them with specified [reducer].
         */
        public fun <V> All(reducer: (values: List<V>) -> V?): ValuesProcessor<V> =
            ValuesProcessor(
                pickingStrategy = PickingStrategy.All(),
                reducingStrategy = ReducingStrategy.Multiple(reducer)
            )
    }
}