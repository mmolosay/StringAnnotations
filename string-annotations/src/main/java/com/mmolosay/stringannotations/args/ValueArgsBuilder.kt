package com.mmolosay.stringannotations.args

import android.text.style.ClickableSpan

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
 * Scope, that provides declarative API for assembling [ValueArgs] instance.
 */
public interface ValueArgsBuilder {

    // region Colors

    /**
     * Adds specified [item] in [ValueArgs.colors] list.
     */
    public fun color(item: Int): ValueArgs

    /**
     * Executes [producer] and adds resulting color integer to [ValueArgs.colors] list.
     */
    public fun color(producer: () -> Int): ValueArgs

    /**
     * Adds specified [items] in [ValueArgs.colors] list.
     */
    public fun colors(vararg items: Int): ValueArgs

    /**
     * Executes [block], scoped to [Adder] of [ValueArgs.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit): ValueArgs

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [ValueArgs.clickables] list.
     */
    public fun clickable(item: ClickableSpan): ValueArgs

    /**
     * Executes [producer] and adds resulting clickable span to [ValueArgs.clickables] list.
     */
    public fun clickable(producer: () -> ClickableSpan): ValueArgs

    /**
     * Adds specified [items] in [ValueArgs.clickables] list.
     */
    public fun clickables(vararg items: ClickableSpan): ValueArgs

    /**
     * Executes [block], scoped to [Adder] of [ValueArgs.clickables] list.
     */
    public fun clickables(block: Adder<ClickableSpan>.() -> Unit): ValueArgs

    // endregion

    // region Typeface styles

    /**
     * Adds specified [item] in [ValueArgs.typefaceStyles] list.
     */
    public fun typefaceStyle(item: Int): ValueArgs

    /**
     * Executes [producer] and adds resulting typeface style integer to [ValueArgs.typefaceStyles] list.
     */
    public fun typefaceStyle(producer: () -> Int): ValueArgs

    /**
     * Adds specified [items] in [ValueArgs.typefaceStyles] list.
     */
    public fun typefaceStyles(vararg items: Int): ValueArgs

    /**
     * Executes [block], scoped to [Adder] of [ValueArgs.typefaceStyles] list.
     */
    public fun typefaceStyles(block: Adder<Int>.() -> Unit): ValueArgs

    // endregion

    // region Absolute sizes

    /**
     * Adds specified [item] in [ValueArgs.absSizes] list.
     */
    public fun absoluteSize(item: Int): ValueArgs

    /**
     * Executes [producer] and adds resulting pixel size to [ValueArgs.absSizes] list.
     */
    public fun absoluteSize(producer: () -> Int): ValueArgs

    /**
     * Adds specified [items] in [ValueArgs.absSizes] list.
     */
    public fun absoluteSizes(vararg items: Int): ValueArgs

    /**
     * Executes [block], scoped to [Adder] of [ValueArgs.absSizes] list.
     */
    public fun absoluteSizes(block: Adder<Int>.() -> Unit): ValueArgs

    // endregion

    /**
     * Scope for adding elements.
     */
    public class Adder<T>(private val dest: MutableList<T>) {

        // overriding unaryPlus operator is quite useless, because if <T> is Int,
        // then global Int.unaryPlus() variant will be used by compiler.

        /**
         * Adds result of executing [producer] to [dest] list.
         */
        public fun add(producer: () -> T) {
            dest.add(producer())
        }

        /**
         * Adds specified [item] to [dest] list.
         */
        public fun add(item: T) {
            dest.add(item)
        }
    }
}