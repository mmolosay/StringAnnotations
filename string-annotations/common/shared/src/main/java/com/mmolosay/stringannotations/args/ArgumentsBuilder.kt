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
 * Scope, that provides declarative API for assembling [Arguments] instance.
 *
 * Note: it should not be used in order to implement builder for custom arguments.
 * One should define fully custom builder component and use it.
 *
 * Each artifact of `StringAnnotations` library must provide their own implementation and
 * resolve all generic types.
 */
public interface ArgumentsBuilder<C : ClickOwner> {

    // region Colors

    /**
     * Adds specified [item] in [Arguments.colors] list.
     */
    public fun color(item: Int): Arguments<C>

    /**
     * Executes [producer] and adds result to [Arguments.colors] list.
     */
    public fun color(producer: () -> Int): Arguments<C>

    /**
     * Adds specified [items] in [Arguments.colors] list.
     */
    public fun colors(vararg items: Int): Arguments<C>

    /**
     * Executes [block], scoped to [Adder] of [Arguments.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit): Arguments<C>

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [Arguments.clickables] list.
     */
    public fun clickable(item: C): Arguments<C>

    /**
     * Executes [producer] and adds result to [Arguments.clickables] list.
     */
    public fun clickable(producer: () -> C): Arguments<C>

    /**
     * Adds specified [items] in [Arguments.clickables] list.
     */
    public fun clickables(vararg items: C): Arguments<C>

    /**
     * Executes [block], scoped to [Adder] of [Arguments.clickables] list.
     */
    public fun clickables(block: Adder<C>.() -> Unit): Arguments<C>

    // endregion

    // region Styles

    /**
     * Adds specified [item] in [Arguments.styles] list.
     */
    public fun style(item: Int): Arguments<C>

    /**
     * Executes [producer] and adds result to [Arguments.styles] list.
     */
    public fun style(producer: () -> Int): Arguments<C>

    /**
     * Adds specified [items] in [Arguments.styles] list.
     */
    public fun styles(vararg items: Int): Arguments<C>

    /**
     * Executes [block], scoped to [Adder] of [Arguments.styles] list.
     */
    public fun styles(block: Adder<Int>.() -> Unit): Arguments<C>

    // endregion

    // region Sizes

    /**
     * Adds specified [item] in [Arguments.sizes] list.
     */
    public fun size(item: TextSize): Arguments<C>

    /**
     * Executes [producer] and adds result to [Arguments.sizes] list.
     */
    public fun size(producer: () -> TextSize): Arguments<C>

    /**
     * Adds specified [items] in [Arguments.sizes] list.
     */
    public fun sizes(vararg items: TextSize): Arguments<C>

    /**
     * Executes [block], scoped to [Adder] of [Arguments.sizes] list.
     */
    public fun sizes(block: Adder<TextSize>.() -> Unit): Arguments<C>

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