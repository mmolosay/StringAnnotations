package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.AnnotationValues
import com.mmolosay.stringannotations.args.AnnotationValuesBuilder
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
 * Implementation of [AnnotationValuesBuilder].
 * Should not be used as explicit type.
 */
public class AnnotationValuesBuilderImpl<C : ClickOwner> : AnnotationValuesBuilder<C> {

    private val args = MutableAnnotationValues<C>()

    private val colorsAdder = AnnotationValuesBuilder.Adder(args.colors)
    private val clickablesAdder = AnnotationValuesBuilder.Adder(args.clickables)
    private val stylesAdder = AnnotationValuesBuilder.Adder(args.styles)
    private val sizesAdder = AnnotationValuesBuilder.Adder(args.sizes)

    // region Colors

    override fun color(item: Int): AnnotationValues<C> =
        add(item, args.colors)

    override fun color(producer: () -> Int): AnnotationValues<C> =
        add(producer, args.colors)

    override fun colors(vararg items: Int): AnnotationValues<C> =
        add(items.toTypedArray(), args.colors)

    override fun colors(block: AnnotationValuesBuilder.Adder<Int>.() -> Unit): AnnotationValues<C> =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: C): AnnotationValues<C> =
        add(item, args.clickables)

    override fun clickable(producer: () -> C): AnnotationValues<C> =
        add(producer, args.clickables)

    override fun clickables(vararg items: C): AnnotationValues<C> =
        add(items, args.clickables)

    override fun clickables(block: AnnotationValuesBuilder.Adder<C>.() -> Unit): AnnotationValues<C> =
        add(block, clickablesAdder)

    // endregion

    // region Styles

    override fun style(item: Int): AnnotationValues<C> =
        add(item, args.styles)

    override fun style(producer: () -> Int): AnnotationValues<C> =
        add(producer, args.styles)

    override fun styles(vararg items: Int): AnnotationValues<C> =
        add(items.toTypedArray(), args.styles)

    override fun styles(block: AnnotationValuesBuilder.Adder<Int>.() -> Unit): AnnotationValues<C> =
        add(block, stylesAdder)

    // endregion

    // region Sizes

    override fun size(item: TextSize): AnnotationValues<C> =
        add(item, args.sizes)

    override fun size(producer: () -> TextSize): AnnotationValues<C> =
        add(producer, args.sizes)

    override fun sizes(vararg items: TextSize): AnnotationValues<C> =
        add(items, args.sizes)

    override fun sizes(block: AnnotationValuesBuilder.Adder<TextSize>.() -> Unit): AnnotationValues<C> =
        add(block, sizesAdder)

    // endregion

    private fun <T> add(
        element: T,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        args.apply {
            dest.add(element)
        }

    private fun <T> add(
        element: Array<out T>,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        args.apply {
            dest.addAll(element)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        args.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: AnnotationValuesBuilder.Adder<T>.() -> Unit,
        adder: AnnotationValuesBuilder.Adder<T>
    ): AnnotationValues<C> =
        args.apply {
            block(adder)
        }
}