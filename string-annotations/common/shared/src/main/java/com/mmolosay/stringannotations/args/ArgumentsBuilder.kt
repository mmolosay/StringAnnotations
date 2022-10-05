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
 * Scope, that provides declarative API for assembling [Arguments] instance.
 */
public interface ArgumentsBuilder {

    // region Colors

    /**
     * Adds specified [item] in [Arguments.colors] list.
     */
    public fun color(item: Int): Arguments

    /**
     * Executes [producer] and adds resulting color integer to [Arguments.colors] list.
     */
    public fun color(producer: () -> Int): Arguments

    /**
     * Adds specified [items] in [Arguments.colors] list.
     */
    public fun colors(vararg items: Int): Arguments

    /**
     * Executes [block], scoped to [Adder] of [Arguments.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit): Arguments

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [Arguments.clickables] list.
     */
    public fun clickable(item: ClickableSpan): Arguments

    /**
     * Executes [producer] and adds resulting clickable span to [Arguments.clickables] list.
     */
    public fun clickable(producer: () -> ClickableSpan): Arguments

    /**
     * Adds specified [items] in [Arguments.clickables] list.
     */
    public fun clickables(vararg items: ClickableSpan): Arguments

    /**
     * Executes [block], scoped to [Adder] of [Arguments.clickables] list.
     */
    public fun clickables(block: Adder<ClickableSpan>.() -> Unit): Arguments

    // endregion

    // region Typeface styles

    /**
     * Adds specified [item] in [Arguments.typefaceStyles] list.
     */
    public fun typefaceStyle(item: Int): Arguments

    /**
     * Executes [producer] and adds resulting typeface style integer to [Arguments.typefaceStyles] list.
     */
    public fun typefaceStyle(producer: () -> Int): Arguments

    /**
     * Adds specified [items] in [Arguments.typefaceStyles] list.
     */
    public fun typefaceStyles(vararg items: Int): Arguments

    /**
     * Executes [block], scoped to [Adder] of [Arguments.typefaceStyles] list.
     */
    public fun typefaceStyles(block: Adder<Int>.() -> Unit): Arguments

    // endregion

    // region Absolute sizes

    /**
     * Adds specified [item] in [Arguments.absSizes] list.
     */
    public fun absoluteSize(item: Float): Arguments

    /**
     * Executes [producer] and adds resulting pixel size to [Arguments.absSizes] list.
     */
    public fun absoluteSize(producer: () -> Float): Arguments

    /**
     * Adds specified [items] in [Arguments.absSizes] list.
     */
    public fun absoluteSizes(vararg items: Float): Arguments

    /**
     * Executes [block], scoped to [Adder] of [Arguments.absSizes] list.
     */
    public fun absoluteSizes(block: Adder<Float>.() -> Unit): Arguments

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