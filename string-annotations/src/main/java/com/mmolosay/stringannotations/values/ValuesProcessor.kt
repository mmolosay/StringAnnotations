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
 * Transforms annotation values of type [V] into final value of the same type.
 * It uses [picking] strategy to pick desired values and [reducing] one to conflate them into
 * final result.
 */
public class ValuesProcessor<V>(
    private val picking: ValuesPickingStrategy<V>,
    private val reducing: ValuesReducingStrategy<V>
) {

    /**
     * Picks desired values, using specified [picking] and then reduces them
     * to one final result, using specified [reducing].
     */
    public fun process(values: Sequence<V>): V? =
        picking.pick(values).let { reducing.reduce(it) }

    public companion object {

        /**
         * Picks and uses very first value.
         */
        public fun <V> Single(): ValuesProcessor<V> =
            ValuesProcessor(
                picking = ValuesPickingStrategy.First(),
                reducing = ValuesReducingStrategy.Single()
            )

        /**
         * Picks all values and reduces them with specified [reducer].
         */
        public fun <V> All(reducer: (values: List<V>) -> V?): ValuesProcessor<V> =
            ValuesProcessor(
                picking = ValuesPickingStrategy.All(),
                reducing = ValuesReducingStrategy.Multiple(reducer)
            )
    }
}