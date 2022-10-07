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

    private val values = MutableAnnotationValues<C>()

    private val colorsAdder = AnnotationValuesBuilder.Adder(values.colors)
    private val clickablesAdder = AnnotationValuesBuilder.Adder(values.clickables)
    private val stylesAdder = AnnotationValuesBuilder.Adder(values.styles)
    private val sizesAdder = AnnotationValuesBuilder.Adder(values.sizes)

    // region Colors

    override fun color(item: Int): AnnotationValues<C> =
        add(item, values.colors)

    override fun color(producer: () -> Int): AnnotationValues<C> =
        add(producer, values.colors)

    override fun colors(vararg items: Int): AnnotationValues<C> =
        add(items.toTypedArray(), values.colors)

    override fun colors(items: Collection<Int>): AnnotationValues<C> =
        add(items, values.colors)

    override fun colors(block: AnnotationValuesBuilder.Adder<Int>.() -> Unit): AnnotationValues<C> =
        add(block, colorsAdder)

    // endregion

    // region Clickables

    override fun clickable(item: C): AnnotationValues<C> =
        add(item, values.clickables)

    override fun clickable(producer: () -> C): AnnotationValues<C> =
        add(producer, values.clickables)

    override fun clickables(vararg items: C): AnnotationValues<C> =
        add(items, values.clickables)

    override fun clickables(items: Collection<C>): AnnotationValues<C> =
        add(items, values.clickables)

    override fun clickables(block: AnnotationValuesBuilder.Adder<C>.() -> Unit): AnnotationValues<C> =
        add(block, clickablesAdder)

    // endregion

    // region Styles

    override fun style(item: Int): AnnotationValues<C> =
        add(item, values.styles)

    override fun style(producer: () -> Int): AnnotationValues<C> =
        add(producer, values.styles)

    override fun styles(vararg items: Int): AnnotationValues<C> =
        add(items.toTypedArray(), values.styles)

    override fun styles(items: Collection<Int>): AnnotationValues<C> =
        add(items, values.styles)

    override fun styles(block: AnnotationValuesBuilder.Adder<Int>.() -> Unit): AnnotationValues<C> =
        add(block, stylesAdder)

    // endregion

    // region Sizes

    override fun size(item: TextSize): AnnotationValues<C> =
        add(item, values.sizes)

    override fun size(producer: () -> TextSize): AnnotationValues<C> =
        add(producer, values.sizes)

    override fun sizes(vararg items: TextSize): AnnotationValues<C> =
        add(items, values.sizes)

    override fun sizes(items: Collection<TextSize>): AnnotationValues<C> =
        add(items, values.sizes)

    override fun sizes(block: AnnotationValuesBuilder.Adder<TextSize>.() -> Unit): AnnotationValues<C> =
        add(block, sizesAdder)

    // endregion

    private fun <T> add(
        element: T,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        values.apply {
            dest.add(element)
        }

    private fun <T> add(
        elements: Array<out T>,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        values.apply {
            dest.addAll(elements)
        }

    private fun <T> add(
        elements: Collection<T>,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        values.apply {
            dest.addAll(elements)
        }

    private fun <T> add(
        producer: () -> T,
        dest: MutableCollection<T>
    ): AnnotationValues<C> =
        values.apply {
            dest.add(producer())
        }

    private fun <T> add(
        block: AnnotationValuesBuilder.Adder<T>.() -> Unit,
        adder: AnnotationValuesBuilder.Adder<T>
    ): AnnotationValues<C> =
        values.apply {
            block(adder)
        }
}