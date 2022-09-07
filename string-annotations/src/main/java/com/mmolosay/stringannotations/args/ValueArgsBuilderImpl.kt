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
 * Internal implementation of [ValueArgsBuilder].
 * Should not be used as explicit type.
 */
internal class ValueArgsBuilderImpl : ValueArgsBuilder {

    private val args = MutableValueArgs()

    private val colorsAdder = ValueArgsBuilder.Adder(args.colors)
    private val clickablesAdder = ValueArgsBuilder.Adder(args.clickables)
    private val typefaceStylesAdder = ValueArgsBuilder.Adder(args.typefaceStyles)
    private val absSizesAdder = ValueArgsBuilder.Adder(args.absSizes)

    // region Colors

    override fun color(item: Int): ValueArgs =
        add(item, args.colors)

    override fun color(producer: () -> Int): ValueArgs =
        add(producer, args.colors)

    override fun colors(vararg items: Int): ValueArgs =
        add(items.toTypedArray(), args.colors)

    override fun colors(block: ValueArgsBuilder.Adder<Int>.() -> Unit): ValueArgs =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: ClickableSpan): ValueArgs =
        add(item, args.clickables)

    override fun clickable(producer: () -> ClickableSpan): ValueArgs =
        add(producer, args.clickables)

    override fun clickables(vararg items: ClickableSpan): ValueArgs =
        add(items, args.clickables)

    override fun clickables(block: ValueArgsBuilder.Adder<ClickableSpan>.() -> Unit): ValueArgs =
        add(block, clickablesAdder)

    // endregion

    // region Typeface styles

    override fun typefaceStyle(item: Int): ValueArgs =
        add(item, args.typefaceStyles)

    override fun typefaceStyle(producer: () -> Int): ValueArgs =
        add(producer, args.typefaceStyles)

    override fun typefaceStyles(vararg items: Int): ValueArgs =
        add(items.toTypedArray(), args.typefaceStyles)

    override fun typefaceStyles(block: ValueArgsBuilder.Adder<Int>.() -> Unit): ValueArgs =
        add(block, typefaceStylesAdder)

    // endregion

    // region Absolute sizes

    override fun absoluteSize(item: Int): ValueArgs =
        add(item, args.absSizes)

    override fun absoluteSize(producer: () -> Int): ValueArgs =
        add(producer, args.absSizes)

    override fun absoluteSizes(vararg items: Int): ValueArgs =
        add(items.toTypedArray(), args.absSizes)

    override fun absoluteSizes(block: ValueArgsBuilder.Adder<Int>.() -> Unit): ValueArgs =
        add(block, absSizesAdder)

    // endregion

    private fun <T> add(
        item: T,
        dest: MutableList<T>
    ): ValueArgs =
        args.apply {
            dest.add(item)
        }

    private fun <T> add(
        items: Array<out T>,
        dest: MutableList<T>
    ): ValueArgs =
        args.apply {
            dest.addAll(items)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableList<T>
    ): ValueArgs =
        args.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: ValueArgsBuilder.Adder<T>.() -> Unit,
        adder: ValueArgsBuilder.Adder<T>
    ): ValueArgs =
        args.apply {
            block(adder)
        }
}