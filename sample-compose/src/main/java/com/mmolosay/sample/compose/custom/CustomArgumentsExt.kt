package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeArguments

fun CustomArguments(
    baseArgs: ComposeArguments,
    builder: CustomArgumentsBuilder.() -> CustomArguments
): CustomArguments =
    builder(CustomArgumentsBuilder((baseArgs)))