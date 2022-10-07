package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationValues
import com.mmolosay.stringannotations.compose.args.ComposeArguments
import com.mmolosay.stringannotations.compose.args.EmptyAnnotationValues

/**
 * Assembles [ComposeArguments] with [CustomAnnotationValues] in declarative style.
 */
fun CustomArguments(
    base: ComposeAnnotationValues = EmptyAnnotationValues(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): ComposeArguments =
    ComposeArguments(CustomAnnotationValues(base, scope))

/**
 * Assembles [CustomAnnotationValues] in declarative style.
 */
fun CustomAnnotationValues(
    base: ComposeAnnotationValues = EmptyAnnotationValues(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): CustomAnnotationValues =
    CustomAnnotationValuesBuilder().apply(scope).build(base)