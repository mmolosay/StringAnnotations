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
 * Scope, that provides declarative API for assembling [AnnotationArguments] instance.
 */
public interface AnnotationArgumentsBuilder {

    // region Colors

    /**
     * Adds specified [item] in [AnnotationArguments.colors] list.
     */
    public fun color(item: Int): AnnotationArguments

    /**
     * Executes [producer] and adds resulting color integer to [AnnotationArguments.colors] list.
     */
    public fun color(producer: () -> Int): AnnotationArguments

    /**
     * Adds specified [items] in [AnnotationArguments.colors] list.
     */
    public fun colors(vararg items: Int): AnnotationArguments

    /**
     * Executes [block], scoped to [Adder] of [AnnotationArguments.colors] list.
     */
    public fun colors(block: Adder<Int>.() -> Unit): AnnotationArguments

    // endregion

    // region Clickables

    /**
     * Adds specified [item] in [AnnotationArguments.clickables] list.
     */
    public fun clickable(item: ClickableSpan): AnnotationArguments

    /**
     * Executes [producer] and adds resulting clickable span to [AnnotationArguments.clickables] list.
     */
    public fun clickable(producer: () -> ClickableSpan): AnnotationArguments

    /**
     * Adds specified [items] in [AnnotationArguments.clickables] list.
     */
    public fun clickables(vararg items: ClickableSpan): AnnotationArguments

    /**
     * Executes [block], scoped to [Adder] of [AnnotationArguments.clickables] list.
     */
    public fun clickables(block: Adder<ClickableSpan>.() -> Unit): AnnotationArguments

    // endregion

    // region Typeface styles

    /**
     * Adds specified [item] in [AnnotationArguments.typefaceStyles] list.
     */
    public fun typefaceStyle(item: Int): AnnotationArguments

    /**
     * Executes [producer] and adds resulting typeface style integer to [AnnotationArguments.typefaceStyles] list.
     */
    public fun typefaceStyle(producer: () -> Int): AnnotationArguments

    /**
     * Adds specified [items] in [AnnotationArguments.typefaceStyles] list.
     */
    public fun typefaceStyles(vararg items: Int): AnnotationArguments

    /**
     * Executes [block], scoped to [Adder] of [AnnotationArguments.typefaceStyles] list.
     */
    public fun typefaceStyles(block: Adder<Int>.() -> Unit): AnnotationArguments

    // endregion

    // region Absolute sizes

    /**
     * Adds specified [item] in [AnnotationArguments.absSizes] list.
     */
    public fun absoluteSize(item: Int): AnnotationArguments

    /**
     * Executes [producer] and adds resulting pixel size to [AnnotationArguments.absSizes] list.
     */
    public fun absoluteSize(producer: () -> Int): AnnotationArguments

    /**
     * Adds specified [items] in [AnnotationArguments.absSizes] list.
     */
    public fun absoluteSizes(vararg items: Int): AnnotationArguments

    /**
     * Executes [block], scoped to [Adder] of [AnnotationArguments.absSizes] list.
     */
    public fun absoluteSizes(block: Adder<Int>.() -> Unit): AnnotationArguments

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