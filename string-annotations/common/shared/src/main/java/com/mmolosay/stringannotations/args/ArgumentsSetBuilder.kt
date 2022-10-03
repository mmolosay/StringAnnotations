package com.mmolosay.stringannotations.args

import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

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
 * Scope, that provides declarative API for assembling [ArgumentSet] instance.
 */
public interface ArgumentsSetBuilder {

    // region Colors

    /**
     * Adds specified [item] in [ArgumentSet.colors] list.
     */
    public fun color(item: Int): ArgumentSet

    /**
     * Executes [producer] and adds resulting color integer to [ArgumentSet.colors] list.
     */
    public fun color(producer: () -> Int): ArgumentSet

    /**
     * Adds specified [items] in [ArgumentSet.colors] list.
     */
    public fun colors(vararg items: Int): ArgumentSet

    /**
     * Executes [block], scoped to [Adder] of [ArgumentSet.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit): ArgumentSet

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [ArgumentSet.clickables] list.
     */
    public fun clickable(item: ClickableSpan): ArgumentSet

    /**
     * Executes [producer] and adds resulting clickable span to [ArgumentSet.clickables] list.
     */
    public fun clickable(producer: () -> ClickableSpan): ArgumentSet

    /**
     * Adds specified [items] in [ArgumentSet.clickables] list.
     */
    public fun clickables(vararg items: ClickableSpan): ArgumentSet

    /**
     * Executes [block], scoped to [Adder] of [ArgumentSet.clickables] list.
     */
    public fun clickables(block: Adder<ClickableSpan>.() -> Unit): ArgumentSet

    // endregion

    // region Typeface styles

    /**
     * Adds specified [item] in [ArgumentSet.typefaceStyles] list.
     */
    public fun typefaceStyle(item: Int): ArgumentSet

    /**
     * Executes [producer] and adds resulting typeface style integer to [ArgumentSet.typefaceStyles] list.
     */
    public fun typefaceStyle(producer: () -> Int): ArgumentSet

    /**
     * Adds specified [items] in [ArgumentSet.typefaceStyles] list.
     */
    public fun typefaceStyles(vararg items: Int): ArgumentSet

    /**
     * Executes [block], scoped to [Adder] of [ArgumentSet.typefaceStyles] list.
     */
    public fun typefaceStyles(block: Adder<Int>.() -> Unit): ArgumentSet

    // endregion

    // region Absolute sizes

    /**
     * Adds specified [item] in [ArgumentSet.absSizes] list.
     */
    public fun absoluteSize(item: Int): ArgumentSet

    /**
     * Executes [producer] and adds resulting pixel size to [ArgumentSet.absSizes] list.
     */
    public fun absoluteSize(producer: () -> Int): ArgumentSet

    /**
     * Adds specified [items] in [ArgumentSet.absSizes] list.
     */
    public fun absoluteSizes(vararg items: Int): ArgumentSet

    /**
     * Executes [block], scoped to [Adder] of [ArgumentSet.absSizes] list.
     */
    public fun absoluteSizes(block: Adder<Int>.() -> Unit): ArgumentSet

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