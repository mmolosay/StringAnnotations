package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeArguments

class CustomArgumentsBuilder(baseArgs: ComposeArguments) {

    private val args = MutableCustomArguments(baseArgs)

    fun custom(item: String): CustomArguments =
        args.apply {
            args.customs.add(item)
        }

    fun customs(vararg items: String): CustomArguments =
        args.apply {
            args.customs.addAll(items)
        }
}