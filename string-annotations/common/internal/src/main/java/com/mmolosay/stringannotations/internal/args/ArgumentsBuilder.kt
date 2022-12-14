package com.mmolosay.stringannotations.internal.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.ArgumentsBuilderScope
import com.mmolosay.stringannotations.args.types.ClickOwner
import com.mmolosay.stringannotations.args.types.TextDecoration
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
 * Implementation of [ArgumentsBuilderScope].
 * Except for inherited functionality, provides [build] method for acquiring assembled [Arguments].
 */
public class ArgumentsBuilder<C : ClickOwner> : ArgumentsBuilderScope<C> {

    private val clickables: MutableList<C> = mutableListOf()
    private val colors: MutableList<Int> = mutableListOf()
    private val decorations: MutableList<TextDecoration> = mutableListOf()
    private val sizes: MutableList<TextSize> = mutableListOf()
    private val styles: MutableList<Int> = mutableListOf()

    private val clickablesAdder = ArgumentsBuilderScope.Adder(clickables)
    private val colorsAdder = ArgumentsBuilderScope.Adder(colors)
    private val decorationsAdder = ArgumentsBuilderScope.Adder(decorations)
    private val sizesAdder = ArgumentsBuilderScope.Adder(sizes)
    private val stylesAdder = ArgumentsBuilderScope.Adder(styles)

    /**
     * Assembles [Arguments].
     * This method is not defined in [ArgumentsBuilderScope] in order to hide
     * it from end user.
     */
    public fun build(): Arguments<C> =
        Arguments(clickables, colors, decorations, sizes, styles)

    // region Clickables

    override fun clickable(item: C) {
        add(item, clickables)
    }

    override fun clickable(producer: () -> C) {
        add(producer, clickables)
    }

    override fun clickables(vararg items: C) {
        add(items, clickables)
    }

    override fun clickables(items: Collection<C>) {
        add(items, clickables)
    }

    override fun clickables(block: ArgumentsBuilderScope.Adder<C>.() -> Unit) {
        add(block, clickablesAdder)
    }

    // endregion

    // region Colors

    override fun color(item: Int) {
        add(item, colors)
    }

    override fun color(producer: () -> Int) {
        add(producer, colors)
    }

    override fun colors(vararg items: Int) {
        add(items.toTypedArray(), colors)
    }

    override fun colors(items: Collection<Int>) {
        add(items, colors)
    }

    override fun colors(block: ArgumentsBuilderScope.Adder<Int>.() -> Unit) {
        add(block, colorsAdder)
    }

    // endregion

    // region Decorations

    override fun decoration(item: TextDecoration) {
        add(item, decorations)
    }

    override fun decoration(producer: () -> TextDecoration) {
        add(producer, decorations)
    }

    override fun decorations(vararg items: TextDecoration) {
        add(items, decorations)
    }

    override fun decorations(items: Collection<TextDecoration>) {
        add(items, decorations)
    }

    override fun decorations(block: ArgumentsBuilderScope.Adder<TextDecoration>.() -> Unit) {
        add(block, decorationsAdder)
    }

    // endregion

    // region Sizes

    override fun size(item: TextSize) {
        add(item, sizes)
    }

    override fun size(producer: () -> TextSize) {
        add(producer, sizes)
    }

    override fun sizes(vararg items: TextSize) {
        add(items, sizes)
    }

    override fun sizes(items: Collection<TextSize>) {
        add(items, sizes)
    }

    override fun sizes(block: ArgumentsBuilderScope.Adder<TextSize>.() -> Unit) {
        add(block, sizesAdder)
    }

    // endregion

    // region Styles

    override fun style(item: Int) {
        add(item, styles)
    }

    override fun style(producer: () -> Int) {
        add(producer, styles)
    }

    override fun styles(vararg items: Int) {
        add(items.toTypedArray(), styles)
    }

    override fun styles(items: Collection<Int>) {
        add(items, styles)
    }

    override fun styles(block: ArgumentsBuilderScope.Adder<Int>.() -> Unit) {
        add(block, stylesAdder)
    }

    // endregion

    private fun <T> add(
        element: T,
        dest: MutableCollection<T>
    ) {
        dest.add(element)
    }

    private fun <T> add(
        elements: Array<out T>,
        dest: MutableCollection<T>
    ) {
        dest.addAll(elements)
    }

    private fun <T> add(
        elements: Collection<T>,
        dest: MutableCollection<T>
    ) {
        dest.addAll(elements)
    }

    private fun <T> add(
        producer: () -> T,
        dest: MutableCollection<T>
    ) {
        dest.add(producer())
    }

    private fun <T> add(
        block: ArgumentsBuilderScope.Adder<T>.() -> Unit,
        adder: ArgumentsBuilderScope.Adder<T>
    ) {
        block(adder)
    }
}