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
 * Internal implementation of [ArgumentsSetBuilder].
 * Should not be used as explicit type.
 */
internal class ArgumentsSetBuilderImpl : ArgumentsSetBuilder {

    private val args = MutableArgumentsSet()

    private val colorsAdder = ArgumentsSetBuilder.Adder(args.colors)
    private val clickablesAdder = ArgumentsSetBuilder.Adder(args.clickables)
    private val typefaceStylesAdder = ArgumentsSetBuilder.Adder(args.typefaceStyles)
    private val absSizesAdder = ArgumentsSetBuilder.Adder(args.absSizes)

    // region Colors

    override fun color(item: Int): ArgumentsSet =
        add(item, args.colors)

    override fun color(producer: () -> Int): ArgumentsSet =
        add(producer, args.colors)

    override fun colors(vararg items: Int): ArgumentsSet =
        add(items.toTypedArray(), args.colors)

    override fun colors(block: ArgumentsSetBuilder.Adder<Int>.() -> Unit): ArgumentsSet =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: ClickableSpan): ArgumentsSet =
        add(item, args.clickables)

    override fun clickable(producer: () -> ClickableSpan): ArgumentsSet =
        add(producer, args.clickables)

    override fun clickables(vararg items: ClickableSpan): ArgumentsSet =
        add(items, args.clickables)

    override fun clickables(block: ArgumentsSetBuilder.Adder<ClickableSpan>.() -> Unit): ArgumentsSet =
        add(block, clickablesAdder)

    // endregion

    // region Typeface styles

    override fun typefaceStyle(item: Int): ArgumentsSet =
        add(item, args.typefaceStyles)

    override fun typefaceStyle(producer: () -> Int): ArgumentsSet =
        add(producer, args.typefaceStyles)

    override fun typefaceStyles(vararg items: Int): ArgumentsSet =
        add(items.toTypedArray(), args.typefaceStyles)

    override fun typefaceStyles(block: ArgumentsSetBuilder.Adder<Int>.() -> Unit): ArgumentsSet =
        add(block, typefaceStylesAdder)

    // endregion

    // region Absolute sizes

    override fun absoluteSize(item: Int): ArgumentsSet =
        add(item, args.absSizes)

    override fun absoluteSize(producer: () -> Int): ArgumentsSet =
        add(producer, args.absSizes)

    override fun absoluteSizes(vararg items: Int): ArgumentsSet =
        add(items.toTypedArray(), args.absSizes)

    override fun absoluteSizes(block: ArgumentsSetBuilder.Adder<Int>.() -> Unit): ArgumentsSet =
        add(block, absSizesAdder)

    // endregion

    private fun <T> add(
        element: T,
        dest: MutableCollection<T>
    ): ArgumentsSet =
        args.apply {
            dest.add(element)
        }

    private fun <T> add(
        element: Array<out T>,
        dest: MutableCollection<T>
    ): ArgumentsSet =
        args.apply {
            dest.addAll(element)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableCollection<T>
    ): ArgumentsSet =
        args.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: ArgumentsSetBuilder.Adder<T>.() -> Unit,
        adder: ArgumentsSetBuilder.Adder<T>
    ): ArgumentsSet =
        args.apply {
            block(adder)
        }
}