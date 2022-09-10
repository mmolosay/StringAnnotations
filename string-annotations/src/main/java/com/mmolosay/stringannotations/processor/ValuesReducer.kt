package com.mmolosay.stringannotations.processor

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
 * Conflates values of type [V] into new value of same type or `null`, if conflation isn't possible.
 */
public fun interface ValuesReducer<V> {

    public fun reduce(values: List<V>): V?

    public companion object {

        /**
         * Picks first value and returns it.
         */
        public fun <V> Single(): ValuesReducer<V> =
            ValuesReducer { values ->
                values.firstOrNull()
            }

        /**
         * Picks all values and reduces them to one.
         */
        public fun <V> Multiple(combiner: (values: List<V>) -> V?): ValuesReducer<V> =
            ValuesReducer(combiner)
    }
}