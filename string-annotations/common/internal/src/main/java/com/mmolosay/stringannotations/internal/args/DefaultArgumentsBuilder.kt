package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentsBuilder
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
 * Implementation of [ArgumentsBuilder].
 * Should not be used as explicit type.
 */
public class DefaultArgumentsBuilder<C : ClickOwner> : ArgumentsBuilder<C> {

    private val args = MutableArguments<C>()

    private val colorsAdder = ArgumentsBuilder.Adder(args.colors)
    private val clickablesAdder = ArgumentsBuilder.Adder(args.clickables)
    private val typefaceStylesAdder = ArgumentsBuilder.Adder(args.typefaceStyles)
    private val absSizesAdder = ArgumentsBuilder.Adder(args.absSizes)

    // region Colors

    override fun color(item: Int): Arguments<C> =
        add(item, args.colors)

    override fun color(producer: () -> Int): Arguments<C> =
        add(producer, args.colors)

    override fun colors(vararg items: Int): Arguments<C> =
        add(items.toTypedArray(), args.colors)

    override fun colors(block: ArgumentsBuilder.Adder<Int>.() -> Unit): Arguments<C> =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: C): Arguments<C> =
        add(item, args.clickables)

    override fun clickable(producer: () -> C): Arguments<C> =
        add(producer, args.clickables)

    override fun clickables(vararg items: C): Arguments<C> =
        add(items, args.clickables)

    override fun clickables(block: ArgumentsBuilder.Adder<C>.() -> Unit): Arguments<C> =
        add(block, clickablesAdder)

    // endregion

    // region Typeface styles

    override fun typefaceStyle(item: Int): Arguments<C> =
        add(item, args.typefaceStyles)

    override fun typefaceStyle(producer: () -> Int): Arguments<C> =
        add(producer, args.typefaceStyles)

    override fun typefaceStyles(vararg items: Int): Arguments<C> =
        add(items.toTypedArray(), args.typefaceStyles)

    override fun typefaceStyles(block: ArgumentsBuilder.Adder<Int>.() -> Unit): Arguments<C> =
        add(block, typefaceStylesAdder)

    // endregion

    // region Absolute sizes

    override fun absoluteSize(item: TextSize): Arguments<C> =
        add(item, args.absSizes)

    override fun absoluteSize(producer: () -> TextSize): Arguments<C> =
        add(producer, args.absSizes)

    override fun absoluteSizes(vararg items: TextSize): Arguments<C> =
        add(items, args.absSizes)

    override fun absoluteSizes(block: ArgumentsBuilder.Adder<TextSize>.() -> Unit): Arguments<C> =
        add(block, absSizesAdder)

    // endregion

    private fun <T> add(
        element: T,
        dest: MutableCollection<T>
    ): Arguments<C> =
        args.apply {
            dest.add(element)
        }

    private fun <T> add(
        element: Array<out T>,
        dest: MutableCollection<T>
    ): Arguments<C> =
        args.apply {
            dest.addAll(element)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableCollection<T>
    ): Arguments<C> =
        args.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: ArgumentsBuilder.Adder<T>.() -> Unit,
        adder: ArgumentsBuilder.Adder<T>
    ): Arguments<C> =
        args.apply {
            block(adder)
        }
}