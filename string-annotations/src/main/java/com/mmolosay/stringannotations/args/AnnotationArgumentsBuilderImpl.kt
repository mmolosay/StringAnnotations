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
 * Internal implementation of [AnnotationArgumentsBuilder].
 * Should not be used as explicit type.
 */
internal class AnnotationArgumentsBuilderImpl : AnnotationArgumentsBuilder {

    private val args = MutableAnnotationArguments()

    private val colorsAdder = AnnotationArgumentsBuilder.Adder(args.colors)
    private val clickablesAdder = AnnotationArgumentsBuilder.Adder(args.clickables)
    private val typefaceStylesAdder = AnnotationArgumentsBuilder.Adder(args.typefaceStyles)
    private val absSizesAdder = AnnotationArgumentsBuilder.Adder(args.absSizes)

    // region Colors

    override fun color(item: Int): AnnotationArguments =
        add(item, args.colors)

    override fun color(producer: () -> Int): AnnotationArguments =
        add(producer, args.colors)

    override fun colors(vararg items: Int): AnnotationArguments =
        add(items.toTypedArray(), args.colors)

    override fun colors(block: AnnotationArgumentsBuilder.Adder<Int>.() -> Unit): AnnotationArguments =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: ClickableSpan): AnnotationArguments =
        add(item, args.clickables)

    override fun clickable(producer: () -> ClickableSpan): AnnotationArguments =
        add(producer, args.clickables)

    override fun clickables(vararg items: ClickableSpan): AnnotationArguments =
        add(items, args.clickables)

    override fun clickables(block: AnnotationArgumentsBuilder.Adder<ClickableSpan>.() -> Unit): AnnotationArguments =
        add(block, clickablesAdder)

    // endregion

    // region Typeface styles

    override fun typefaceStyle(item: Int): AnnotationArguments =
        add(item, args.typefaceStyles)

    override fun typefaceStyle(producer: () -> Int): AnnotationArguments =
        add(producer, args.typefaceStyles)

    override fun typefaceStyles(vararg items: Int): AnnotationArguments =
        add(items.toTypedArray(), args.typefaceStyles)

    override fun typefaceStyles(block: AnnotationArgumentsBuilder.Adder<Int>.() -> Unit): AnnotationArguments =
        add(block, typefaceStylesAdder)

    // endregion

    // region Absolute sizes

    override fun absoluteSize(item: Int): AnnotationArguments =
        add(item, args.absSizes)

    override fun absoluteSize(producer: () -> Int): AnnotationArguments =
        add(producer, args.absSizes)

    override fun absoluteSizes(vararg items: Int): AnnotationArguments =
        add(items.toTypedArray(), args.absSizes)

    override fun absoluteSizes(block: AnnotationArgumentsBuilder.Adder<Int>.() -> Unit): AnnotationArguments =
        add(block, absSizesAdder)

    // endregion

    private fun <T> add(
        item: T,
        dest: MutableList<T>
    ): AnnotationArguments =
        args.apply {
            dest.add(item)
        }

    private fun <T> add(
        items: Array<out T>,
        dest: MutableList<T>
    ): AnnotationArguments =
        args.apply {
            dest.addAll(items)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableList<T>
    ): AnnotationArguments =
        args.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: AnnotationArgumentsBuilder.Adder<T>.() -> Unit,
        adder: AnnotationArgumentsBuilder.Adder<T>
    ): AnnotationArguments =
        args.apply {
            block(adder)
        }
}