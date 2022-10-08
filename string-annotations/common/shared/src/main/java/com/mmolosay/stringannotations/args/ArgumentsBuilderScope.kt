package com.mmolosay.stringannotations.args

import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextSize

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
 * Provides declarative API for assembling [Arguments] instance.
 * Has no `build` method, since designed to be used as `Kotlin scope`.
 */
public interface ArgumentsBuilderScope<C : ClickOwner> {

    // region Colors

    /**
     * Adds specified [item] in [Arguments.colors] list.
     */
    public fun color(item: Int)

    /**
     * Executes [producer] and adds result to [Arguments.colors] list.
     */
    public fun color(producer: () -> Int)

    /**
     * Adds specified [items] in [Arguments.colors] list.
     */
    public fun colors(vararg items: Int)

    /**
     * Adds specified [items] in [Arguments.colors] list.
     */
    public fun colors(items: Collection<Int>)

    /**
     * Executes [block], scoped to [Adder] of [Arguments.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit)

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [Arguments.clickables] list.
     */
    public fun clickable(item: C)

    /**
     * Executes [producer] and adds result to [Arguments.clickables] list.
     */
    public fun clickable(producer: () -> C)

    /**
     * Adds specified [items] in [Arguments.clickables] list.
     */
    public fun clickables(vararg items: C)

    /**
     * Adds specified [items] in [Arguments.clickables] list.
     */
    public fun clickables(items: Collection<C>)

    /**
     * Executes [block], scoped to [Adder] of [Arguments.clickables] list.
     */
    public fun clickables(block: Adder<C>.() -> Unit)

    // endregion

    // region Styles

    /**
     * Adds specified [item] in [Arguments.styles] list.
     */
    public fun style(item: Int)

    /**
     * Executes [producer] and adds result to [Arguments.styles] list.
     */
    public fun style(producer: () -> Int)

    /**
     * Adds specified [items] in [Arguments.styles] list.
     */
    public fun styles(vararg items: Int)

    /**
     * Adds specified [items] in [Arguments.styles] list.
     */
    public fun styles(items: Collection<Int>)

    /**
     * Executes [block], scoped to [Adder] of [Arguments.styles] list.
     */
    public fun styles(block: Adder<Int>.() -> Unit)

    // endregion

    // region Sizes

    /**
     * Adds specified [item] in [Arguments.sizes] list.
     */
    public fun size(item: TextSize)

    /**
     * Executes [producer] and adds result to [Arguments.sizes] list.
     */
    public fun size(producer: () -> TextSize)

    /**
     * Adds specified [items] in [Arguments.sizes] list.
     */
    public fun sizes(vararg items: TextSize)

    /**
     * Adds specified [items] in [Arguments.sizes] list.
     */
    public fun sizes(items: Collection<TextSize>)

    /**
     * Executes [block], scoped to [Adder] of [Arguments.sizes] list.
     */
    public fun sizes(block: Adder<TextSize>.() -> Unit)

    // endregion

    /**
     * Scope for adding elements.
     */
    public class Adder<E>(private val dest: MutableCollection<E>) {

        // overriding unaryPlus operator is quite useless, because if <T> is Int,
        // then global Int.unaryPlus() variant will be used by compiler.

        /**
         * Adds result of executing [producer] to [dest] list.
         */
        public fun add(producer: () -> E) {
            dest.add(producer())
        }

        /**
         * Adds specified [element] to [dest] list.
         */
        public fun add(element: E) {
            dest.add(element)
        }
    }
}